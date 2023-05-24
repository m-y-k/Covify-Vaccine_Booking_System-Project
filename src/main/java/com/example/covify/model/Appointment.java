package com.example.covify.model;

import com.example.covify.enums.DoseNo;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "appointment_no")
    @GeneratedValue(strategy = GenerationType.UUID)
    String appointmentNo;

    @Column(name = "date_of_appointment")
    @CreationTimestamp
    Date dateOfAppointment;

    @Column(name = "dose_no")
    @Enumerated(EnumType.STRING)
    DoseNo doseNo;

    // creating relation-ship with user
    @ManyToOne
    @JoinColumn
    User user;

    // creating relation-ship with doctor
    @ManyToOne
    @JoinColumn
    Doctor doctor;

    // creating relation-ship with vaccination center
    @ManyToOne
    @JoinColumn
    VaccinationCenter vaccinationCenter;
}
