package com.example.covify.service;

import com.example.covify.dto.requestDTO.DoctorRequestDto;
import com.example.covify.dto.responseDTO.DoctorResponseDto;
import com.example.covify.exception.CenterNotFoundException;
import com.example.covify.model.Doctor;

import java.util.List;

public interface DoctorService {

    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) throws CenterNotFoundException;

    public List<String> getAllDoctorsWithAppointmentsMoreThanX(Integer x);

    public List<String> getAllDoctorsWithAgeMoreThanX(String gender, Integer age);

    public double getDoctorsMaleToFemaleRatio();

    DoctorResponseDto updateDoctor(Integer id, DoctorRequestDto doctorRequestDto);

    public String deleteDoctorById(Integer id);
}
