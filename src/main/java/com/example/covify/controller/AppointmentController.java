package com.example.covify.controller;

import com.example.covify.dto.requestDTO.AppointmentRequestDto;
import com.example.covify.dto.responseDTO.AppointmentResponseDto;
import com.example.covify.exception.*;
import com.example.covify.service.impl.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentServiceImpl appointmentService;

    // POST

    // 1. book an appointment
    @PostMapping("/book")
    public ResponseEntity bookAppointment(@RequestBody AppointmentRequestDto appointmentRequestDto) {

        try {
            AppointmentResponseDto appointmentResponseDto = appointmentService.bookAppointment(appointmentRequestDto);
            return new ResponseEntity<>(appointmentResponseDto, HttpStatus.ACCEPTED);
        }catch (Dose1NotTakenException | UserNotFoundException | DoctorNotFoundException | Dose1AlreadyBookedException |
                Dose2AlreadyBookedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // This will make the project very good
    // 2. generate certificate, its ok if i don't make different controller
        // make a different certificate class, bcz i have to save info in db
        // -> One dose -- Post
        // -> both doses -- Put
}
