package com.example.covify.dto.responseDTO;

import com.example.covify.enums.DoseNo;
import com.example.covify.enums.VaccineType;
import com.example.covify.model.Doctor;
import com.example.covify.model.User;
import com.example.covify.model.VaccinationCenter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AppointmentResponseDto {

    String userName;

    String appointmentNo;

    Date dateOfAppointment;

    DoseNo doseNo;

    CenterResponseDto centerResponseDto;

    String doctorName;

    VaccineType vaccineType;

}
