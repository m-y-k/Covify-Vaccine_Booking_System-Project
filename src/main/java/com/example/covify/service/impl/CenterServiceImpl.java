package com.example.covify.service.impl;

import com.example.covify.dto.requestDTO.CenterRequestDto;
import com.example.covify.dto.responseDTO.CenterResponseDto;
import com.example.covify.dto.responseDTO.DoctorResponseDto;
import com.example.covify.model.Doctor;
import com.example.covify.model.VaccinationCenter;
import com.example.covify.repository.CenterRepository;
import com.example.covify.repository.DoctorRepository;
import com.example.covify.service.CenterService;
import com.example.covify.transformer.DoctorTransformer;
import com.example.covify.transformer.VaccinationCenterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CenterServiceImpl implements CenterService {

    @Autowired
    CenterRepository centerRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto) {

        // dto to entity
        VaccinationCenter vaccinationCenter = VaccinationCenterTransformer.centerRequestDtoToCenter(centerRequestDto);

        // save in database
        VaccinationCenter savedCenter = centerRepository.save(vaccinationCenter);

        // entity to dto
        return VaccinationCenterTransformer.centerToCentreResponseDto(savedCenter);
    }

    @Override
    public List<DoctorResponseDto> getAllDoctorsAtCenter(Integer id) {

        Optional<VaccinationCenter> vaccinationCenter = centerRepository.findAllById(id);

        List<Doctor> doctors = vaccinationCenter.get().getDoctors();

        List<DoctorResponseDto> doctorResponseDtoList = new ArrayList<>();

        for (Doctor doctor : doctors) {
            doctorResponseDtoList.add(DoctorTransformer.doctorToDoctorResponseDto(doctor));
        }
        return doctorResponseDtoList;
    }

    @Override
    public List<DoctorResponseDto> getAllGenderDoctorsAtCenter(String gender, Integer id) {

        List<Doctor> doctors = doctorRepository.findAllByGenderAndVaccinationCenterId(gender, id);

        List<DoctorResponseDto> doctorResponseDtoList = new ArrayList<>();

        for (Doctor doctor : doctors) {
            doctorResponseDtoList.add(DoctorTransformer.doctorToDoctorResponseDto(doctor));
        }
        return doctorResponseDtoList;
    }

    @Override
    public List<DoctorResponseDto> getAllGenderDoctorsAtCenterAgeAboveX(String gender, Integer age, Integer id) {

        List<Doctor> doctors = doctorRepository.findAllByGenderAndAgeAndVaccinationCenterId(gender, age, id);

        List<DoctorResponseDto> doctorResponseDtoList = new ArrayList<>();

        for (Doctor doctor : doctors) {
            doctorResponseDtoList.add(DoctorTransformer.doctorToDoctorResponseDto(doctor));
        }
        return doctorResponseDtoList;
    }

    @Override
    public List<CenterResponseDto> getAllCentersAsCenter(Integer id) {

        List<VaccinationCenter> centers = centerRepository.findAllById(Collections.singleton(id));

        List<CenterResponseDto> centerResponseDtoList = new ArrayList<>();

        for (VaccinationCenter center : centers) {
            centerResponseDtoList.add(VaccinationCenterTransformer.centerToCentreResponseDto(center));
        }
        return centerResponseDtoList;
    }


}
