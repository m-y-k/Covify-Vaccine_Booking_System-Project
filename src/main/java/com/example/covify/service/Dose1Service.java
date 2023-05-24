package com.example.covify.service;

import com.example.covify.enums.VaccineType;
import com.example.covify.model.Dose1;
import com.example.covify.model.User;

public interface Dose1Service {

    public Dose1 createDose1(User user, VaccineType vaccineType);
}
