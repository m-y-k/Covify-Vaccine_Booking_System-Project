package com.example.covify.service;


import com.example.covify.dto.requestDTO.CenterRequestDto;
import com.example.covify.dto.responseDTO.CenterResponseDto;
import com.example.covify.dto.responseDTO.DoctorResponseDto;

import java.util.List;

public interface CenterService {

    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto);

    List<DoctorResponseDto> getAllDoctorsAtCenter(Integer id);

    List<DoctorResponseDto> getAllGenderDoctorsAtCenter(String gender, Integer id);

    List<DoctorResponseDto> getAllGenderDoctorsAtCenterAgeAboveX(String gender, Integer age, Integer id);

    List<CenterResponseDto> getAllCentersAsCenter(Integer id);
}
