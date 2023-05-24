package com.example.covify.service.impl;

import com.example.covify.enums.VaccineType;
import com.example.covify.model.Dose2;
import com.example.covify.model.User;
import com.example.covify.service.Dose2Service;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Dose2ServiceImpl implements Dose2Service {


    @Override
    public Dose2 createDose2(User user, VaccineType vaccineType) {

        return Dose2.builder()
                .doseId(String.valueOf(UUID.randomUUID()))
                .user(user)
                .vaccinationType(vaccineType)
                .build();
    }
}
