package com.example.covify.transformer;

import com.example.covify.dto.requestDTO.AppointmentRequestDto;
import com.example.covify.dto.responseDTO.AppointmentResponseDto;
import com.example.covify.dto.responseDTO.CenterResponseDto;
import com.example.covify.enums.DoseNo;
import com.example.covify.enums.VaccineType;
import com.example.covify.exception.Dose1NotTakenException;
import com.example.covify.model.Appointment;
import com.example.covify.model.Doctor;
import com.example.covify.model.User;
import com.example.covify.repository.UserRepository;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@UtilityClass
public class AppointmentTransformer {

    @Autowired
    UserRepository userRepository;

    public static Appointment appointmentRequestDtoToAppointment(AppointmentRequestDto appointmentRequestDto) {

        return Appointment.builder()
                .appointmentNo(String.valueOf(UUID.randomUUID()))
                .doseNo(appointmentRequestDto.getDoseNo())
                .build();
    }

    public static AppointmentResponseDto appointmentToAppointmentResponseDto(Appointment appointment) {

        CenterResponseDto centerResponseDto = VaccinationCenterTransformer.centerToCentreResponseDto(appointment.getVaccinationCenter());

        return AppointmentResponseDto.builder()
                .userName(appointment.getUser().getName())
                .appointmentNo(appointment.getAppointmentNo())
                .doctorName(appointment.getDoctor().getName())
                .dateOfAppointment(appointment.getDateOfAppointment())
                .doseNo(appointment.getDoseNo())
                .centerResponseDto(centerResponseDto)
                .build();
    }

    public static Appointment makeAppointment(AppointmentRequestDto appointmentRequestDto, User user, Doctor doctor) {
        Appointment appointment = AppointmentTransformer.appointmentRequestDtoToAppointment(appointmentRequestDto);
        appointment.setUser(user);
        appointment.setDoctor(doctor);
        appointment.setVaccinationCenter(doctor.getVaccinationCenter());
        return appointment;
    }
    public AppointmentResponseDto appointmentBooking(Appointment bookedAppointment) throws Dose1NotTakenException {
//        userRepository.save(bookedAppointment.getUser()); // save dose1/dose2 and appointment in user db
        AppointmentResponseDto appointmentResponseDto = AppointmentTransformer.appointmentToAppointmentResponseDto(bookedAppointment);
        if (bookedAppointment.getDoseNo() == DoseNo.DOSE_1) {
            appointmentResponseDto.setVaccineType(bookedAppointment.getUser().getDose1().getVaccinationType());
        }else {
            // checking if user is taken dose1 or not
            if (!bookedAppointment.getUser().is_dose1_taken()) {
                throw new Dose1NotTakenException("You haven't booked Dose 1, please book it first!!!!");
            }
            appointmentResponseDto.setVaccineType(bookedAppointment.getUser().getDose2().getVaccinationType());
        }
        appointmentResponseDto.setAppointmentNo(bookedAppointment.getAppointmentNo());

        return appointmentResponseDto;
    }
}
