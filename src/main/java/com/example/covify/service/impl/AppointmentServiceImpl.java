package com.example.covify.service.impl;

import com.example.covify.dto.requestDTO.AppointmentRequestDto;
import com.example.covify.dto.responseDTO.AppointmentResponseDto;
import com.example.covify.enums.DoseNo;
import com.example.covify.exception.*;
import com.example.covify.model.*;
import com.example.covify.repository.AppointmentRepository;
import com.example.covify.repository.DoctorRepository;
import com.example.covify.repository.UserRepository;
import com.example.covify.service.AppointmentService;
import com.example.covify.transformer.AppointmentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired Dose1ServiceImpl dose1Service;

    @Autowired Dose2ServiceImpl dose2Service;

    @Autowired
    JavaMailSender mailSender;
    @Override
    public AppointmentResponseDto bookAppointment(AppointmentRequestDto appointmentRequestDto) throws UserNotFoundException, DoctorNotFoundException, Dose1NotTakenException, Dose1AlreadyBookedException, Dose2AlreadyBookedException {

        // 1. check if user exists
        Optional<User> optionalUser = userRepository.findById(appointmentRequestDto.getUserId());
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("User not found for Id : " + appointmentRequestDto.getUserId());
        }

        // 2. check if doctor exists
        Optional<Doctor> optionalDoctor = doctorRepository.findById(appointmentRequestDto.getDoctorId());
        if (!optionalDoctor.isPresent()) {
            throw new DoctorNotFoundException("Doctor not found for Id : " + appointmentRequestDto.getDoctorId());
        }

        // 3. if both exists, book appointment
        User user = optionalUser.get();
        Doctor doctor = optionalDoctor.get();

        // check for the request id for dose1 or dose2

        if (appointmentRequestDto.getDoseNo() == DoseNo.DOSE_1) {

            // check if the user already taken dose 1
            if (user.is_dose1_taken()) {
                throw new Dose1AlreadyBookedException("Dose 1 already taken, please book dose 2!!!");
            }
            // book 1st dose for the user
            Dose1 dose1 = dose1Service.createDose1(user, appointmentRequestDto.getVaccineType());
            user.set_dose1_taken(true);
            user.setDose1(dose1);
        }else {
            // check if the user taken dose 1 or not
            if (!user.is_dose1_taken()) {
                throw new Dose1NotTakenException("Dose 1 not taken, please book dose 1!!!");
            }
            // check if dose 2 is already taken
            if (user.is_dose2_taken()) {
                throw new Dose2AlreadyBookedException("Dose 2 already taken");
            }
            // book 2nd dose
            Dose2 dose2 = dose2Service.createDose2(user, appointmentRequestDto.getVaccineType());
            user.set_dose2_taken(true);
            user.setDose2(dose2);
//            // add appointment in doctor's appointment list
//            Appointment appointment = AppointmentTransformer.appointmentRequestDtoToAppointment(appointmentRequestDto);
//            appointment.setUser(user);
//            appointment.setDoctor(doctor);
//            appointment.setVaccinationCenter(doctor.getVaccinationCenter());
//
//            Appointment bookedAppointment = appointmentRepository.save(appointment);
//            doctor.getAppointments().add(bookedAppointment);
//            AppointmentResponseDto appointmentResponseDto = AppointmentTransformer.appointmentToAppointmentResponseDto(bookedAppointment);
//            appointmentResponseDto.setVaccineType(appointmentRequestDto.getVaccineType());
//            appointmentResponseDto.setAppointmentNo(bookedAppointment.getAppointmentNo());
//            return appointmentResponseDto;
            // call function for booking appointment
        }

        // call function for booking appointment
        Appointment appointment = AppointmentTransformer.makeAppointment(appointmentRequestDto, user, doctor);
        Appointment bookedAppointment = appointmentRepository.save(appointment);
        // add appointment in doctor's and user's appointment list
        user.getAppointments().add(bookedAppointment);
        User savedUser = userRepository.save(user); // save dose1/dose2 and appointment in user db
        doctor.getAppointments().add(savedUser.getAppointments().get(savedUser.getAppointments().size() - 1));
        doctorRepository.save(doctor);

        // send email
//        EmailService.sendSimpleMessage(appointment);
        // separate class for Email sender is giving null pointer exception
        String text = "Congratulations, your slot for vaccination is booked with username -" + appointment.getUser().getName()
                + " for " + appointment.getDoseNo() + "."
                + " You Appointment id - " + appointment.getAppointmentNo() + "."
                + " The doctor appointed to you is " + appointment.getDoctor().getName()
                + " at the center - " + appointment.getVaccinationCenter().getName()
                + " which is located at - " + appointment.getVaccinationCenter().getLocation();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("myk220897@gmail.com");
        message.setTo(appointment.getUser().getEmailId());
        message.setSubject("Your Vaccination is booked successfully!!");
        message.setText(text);
        mailSender.send(message);

        return AppointmentTransformer.appointmentBooking(bookedAppointment);
    }


}
