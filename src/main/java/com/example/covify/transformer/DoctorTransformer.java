package com.example.covify.transformer;

import com.example.covify.dto.requestDTO.DoctorRequestDto;
import com.example.covify.dto.responseDTO.CenterResponseDto;
import com.example.covify.dto.responseDTO.DoctorResponseDto;
import com.example.covify.model.Doctor;
import com.example.covify.model.VaccinationCenter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DoctorTransformer {

    public static Doctor doctorRequestDtoToDoctor(DoctorRequestDto doctorRequestDto) {

        return Doctor.builder()
                .age(doctorRequestDto.getAge())
                .name(doctorRequestDto.getName())
                .emailId(doctorRequestDto.getEmailId())
                .mobileNo(doctorRequestDto.getMobileNo())
                .gender(doctorRequestDto.getGender())
                .build();
    }

    public static DoctorResponseDto doctorToDoctorResponseDto(Doctor doctor) {

        CenterResponseDto centerResponseDto = VaccinationCenterTransformer.centerToCentreResponseDto(doctor.getVaccinationCenter());

        return DoctorResponseDto.builder()
                .name(doctor.getName())
                .emailId(doctor.getEmailId())
                .mobileNo(doctor.getMobileNo())
                .centerResponseDto(centerResponseDto)
                .build();
    }
}
