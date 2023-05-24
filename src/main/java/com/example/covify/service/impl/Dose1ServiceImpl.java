package com.example.covify.service.impl;

import com.example.covify.enums.VaccineType;
import com.example.covify.model.Dose1;
import com.example.covify.model.User;
import com.example.covify.service.Dose1Service;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Dose1ServiceImpl implements Dose1Service {


    @Override
    public Dose1 createDose1(User user, VaccineType vaccineType) {

        // do it with dto

        return Dose1.builder()
                .doseId(String.valueOf(UUID.randomUUID()))
                .vaccinationType(vaccineType)
                .user(user)
                .build();
    }
}
