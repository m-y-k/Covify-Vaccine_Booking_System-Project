package com.example.covify.model;

import com.example.covify.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.BatchSize;
import org.springframework.boot.convert.DataSizeUnit;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "user")
@Builder
public class User {

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
    @Size(min = 10, max = 13)
    String mobileNo;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(name = "is_dose1_taken")
    boolean is_dose1_taken;

    @Column(name = "is_dose2_taken")
    boolean is_dose2_taken;

    // creating relation-ship with appointment
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Appointment> appointments = new ArrayList<>();

    // creating relation-ship with dose1
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Dose1 dose1;

    // creating ralation-ship with dose2
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Dose2 dose2;
}
