package com.example.covify.service;

import com.example.covify.enums.VaccineType;
import com.example.covify.model.Dose2;
import com.example.covify.model.User;

public interface Dose2Service {

    public Dose2 createDose2(User user, VaccineType vaccineType);
}
