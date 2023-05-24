package com.example.covify.model;

import com.example.covify.enums.VaccineType;
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
public class Dose2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "dose_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    String doseId;

    @Column(name = "vaccination_type")
    @Enumerated(EnumType.STRING)
    VaccineType vaccinationType;

    @Column(name = "vaccination_date")
    @CreationTimestamp
    Date vaccinationDate;

    // creating relation-ship with user
    @OneToOne
    @JoinColumn
    User user;
}
