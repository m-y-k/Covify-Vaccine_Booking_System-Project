package com.example.covify.model;

import com.example.covify.enums.CenterType;
import com.example.covify.enums.Gender;
import com.example.covify.enums.VaccineType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VaccinationCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "location")
    String location;

    @Column(name = "center_type")
    @Enumerated(EnumType.STRING)
    CenterType centerType;


    // creating relation-ship with doctor
    @OneToMany(mappedBy = "vaccinationCenter", cascade = CascadeType.ALL)
    List<Doctor> doctors = new ArrayList<>();

    // creating relation-ship with appointment
    @OneToMany(mappedBy = "vaccinationCenter", cascade = CascadeType.ALL)
    List<Appointment> appointments = new ArrayList<>();
}
