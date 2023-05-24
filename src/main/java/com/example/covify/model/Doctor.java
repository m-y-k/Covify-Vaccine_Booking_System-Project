package com.example.covify.model;

import com.example.covify.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "age")
    int age;

    @Column(name = "email_id", unique = true, nullable = false)
    String emailId;

    @Column(name = "contact_no", unique = true, nullable = false)
    // add char limit in contact number
    String mobileNo;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    Gender gender;

    // creating relation-ship with appointment
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    List<Appointment> appointments = new ArrayList<>();

    // creating relation-ship with vaccination center
    @ManyToOne
    @JoinColumn
    VaccinationCenter vaccinationCenter;

}
