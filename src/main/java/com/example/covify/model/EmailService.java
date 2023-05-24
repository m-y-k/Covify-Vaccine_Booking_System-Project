package com.example.covify.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class EmailService {

    @Autowired
    static JavaMailSender emailSender;


    public void sendSimpleMessage(Appointment appointment) {
        String text = "Congratulations, your slot for vaccination is booked with username -" + appointment.getUser().getName()
                + " for " + appointment.getDoseNo() + "."
                + " You Appointment id - " + appointment.getAppointmentNo() + "."
                + " The doctor appointed to you is " + appointment.getDoctor().getName()
                + " at the center - " + appointment.getVaccinationCenter().getName()
                + " which is located at - " + appointment.getVaccinationCenter().getLocation();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("myk220897@gmail.com");
        message.setTo(appointment.getUser().getEmailId());
        message.setSubject("Your Vaccination is booked successfully!!");
        message.setText(text);
        emailSender.send(message);
    }
}
