package com.example.covify.service.impl;

import com.example.covify.dto.requestDTO.DoctorRequestDto;
import com.example.covify.dto.responseDTO.DoctorResponseDto;
import com.example.covify.exception.CenterNotFoundException;
import com.example.covify.model.Appointment;
import com.example.covify.model.Doctor;
import com.example.covify.model.VaccinationCenter;
import com.example.covify.repository.AppointmentRepository;
import com.example.covify.repository.CenterRepository;
import com.example.covify.repository.DoctorRepository;
import com.example.covify.service.DoctorService;
import com.example.covify.transformer.DoctorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    CenterRepository centerRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    AppointmentRepository appointmentRepository;


    @Override
    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) throws CenterNotFoundException {

        VaccinationCenter center = centerRepository.findById(doctorRequestDto.getCenterId()).orElseThrow(() -> new CenterNotFoundException("Center invalid!"));

        // dto -> entity
        Doctor doctor = DoctorTransformer.doctorRequestDtoToDoctor(doctorRequestDto);
        doctor.setVaccinationCenter(center);

        // add doctor to the list of doctors present in that vaccination center
        center.getDoctors().add(doctor);

        // save center and doctor both to center database
//        VaccinationCenter savedCenter = centerRepository.save(center);

        Doctor savedDoctor = doctorRepository.save(doctor);

        // entity -> dto
        return DoctorTransformer.doctorToDoctorResponseDto(savedDoctor);
    }

    @Override
    public List<String> getAllDoctorsWithAppointmentsMoreThanX(Integer x) {

        // get list of all doctors with their appointment
            // filter based on appoint size

        List<Appointment> appointments = appointmentRepository.findAll();
        // use hashmap for doctor and appointments trace
            // key - doctor id : value - appointment count
        HashMap<Integer, Integer> doctorAppointmentMap = new HashMap<>();

        List<String> doctors = new ArrayList<>();

        for (Appointment appointment : appointments) {

            Integer key = appointment.getDoctor().getId();

            // update appoint number against doctor
            doctorAppointmentMap.put(key, doctorAppointmentMap.getOrDefault(key, 0) + 1);

            // whenever doctor gets appointments > x, put his name in the list
            if (doctorAppointmentMap.get(key) > x) {
                doctors.add(appointment.getDoctor().getName());
            }
        }

        return doctors;
    }

    @Override
    public List<String> getAllDoctorsWithAgeMoreThanX(String gender, Integer age) {

        // get all doctors list and filter acc to the gender and age

        List<Doctor> doctors = doctorRepository.getAllDoctorsWithAgeMoreThanX(gender, age);
        List<String> doctorList = new ArrayList<>();

        for (Doctor doctor : doctors) {
                doctorList.add(doctor.getName());
        }
        return doctorList;
    }


    @Override
    public double getDoctorsMaleToFemaleRatio() {

        // get all doctor with gender
            // return their ratio

        List<Doctor> maleDoctors = doctorRepository.getAllDoctorsWithGender("male");
        List<Doctor> femaleDoctors = doctorRepository.getAllDoctorsWithGender("female");

        return (double) maleDoctors.size() / femaleDoctors.size();
    }

    @Override
    public DoctorResponseDto updateDoctor(Integer id, DoctorRequestDto doctorRequestDto) {

        // find doctor first
        Doctor doctor = doctorRepository.findById(id).get();

        // update details in new Doctor
        doctor.setAge(doctorRequestDto.getAge());
        doctor.setGender(doctorRequestDto.getGender());
        doctor.setEmailId(doctorRequestDto.getEmailId());
        doctor.setName(doctorRequestDto.getName());
        doctor.setMobileNo(doctorRequestDto.getMobileNo());

        Doctor savedDoctor = doctorRepository.save(doctor);

        return DoctorTransformer.doctorToDoctorResponseDto(savedDoctor);
    }

    @Override
    public String deleteDoctorById(Integer id) {

        doctorRepository.deleteById(id);
        return "The doctor with id :" + id + " has been deleted successfully!!";
    }


}
