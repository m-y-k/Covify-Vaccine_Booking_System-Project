package com.example.covify.transformer;

import com.example.covify.dto.requestDTO.CenterRequestDto;
import com.example.covify.dto.responseDTO.CenterResponseDto;
import com.example.covify.model.VaccinationCenter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VaccinationCenterTransformer {


    public static VaccinationCenter centerRequestDtoToCenter(CenterRequestDto centerRequestDto) {

        return VaccinationCenter.builder()
                .centerType(centerRequestDto.getCenterType())
                .name(centerRequestDto.getName())
                .location(centerRequestDto.getLocation())
                .build();
    }

    public static CenterResponseDto centerToCentreResponseDto(VaccinationCenter vaccinationCenter) {

        return CenterResponseDto.builder()
                .name(vaccinationCenter.getName())
                .location(vaccinationCenter.getLocation())
                .centerType(vaccinationCenter.getCenterType())
                .build();
    }

}
