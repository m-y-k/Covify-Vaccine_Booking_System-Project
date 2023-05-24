package com.example.covify.service;

import com.example.covify.dto.requestDTO.AppointmentRequestDto;
import com.example.covify.dto.responseDTO.AppointmentResponseDto;
import com.example.covify.exception.*;

public interface AppointmentService {

    public AppointmentResponseDto bookAppointment(AppointmentRequestDto appointmentRequestDto) throws UserNotFoundException, DoctorNotFoundException, Dose1NotTakenException, Dose1AlreadyBookedException, Dose2AlreadyBookedException;
}
