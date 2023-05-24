package com.example.covify.controller;

import com.example.covify.dto.requestDTO.DoctorRequestDto;
import com.example.covify.dto.responseDTO.DoctorResponseDto;
import com.example.covify.exception.CenterNotFoundException;
import com.example.covify.model.Doctor;
import com.example.covify.service.impl.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorServiceImpl doctorService;

    // POST

    // add a doctor
    @PostMapping("/add")
    public ResponseEntity addDoctor(@RequestBody DoctorRequestDto doctorRequestDto) {

        try {
            DoctorResponseDto doctorResponseDto = doctorService.addDoctor(doctorRequestDto);
            return new ResponseEntity<>(doctorResponseDto, HttpStatus.CREATED);
        }catch (CenterNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // GET

    // get all the doctors who have > {x} appointments
    @GetMapping("/get-all-appointments-more-than-{x}")
    public ResponseEntity getAllDoctorsWithAppointmentsMoreThanX(@PathVariable Integer x) {

        List<String> doctorList = doctorService.getAllDoctorsWithAppointmentsMoreThanX(x);
        return new ResponseEntity<>(doctorList, HttpStatus.FOUND);
    }

    // get all the {gender} doctors whose age is above {x}
    @GetMapping("/all-{gender}-with-age-more-than-{age}")
    public ResponseEntity getAllDoctorsWithAgeMoreThanX(@PathVariable("gender") String gender,
                                                        @PathVariable("age") Integer age) {
        // one can pass gender in small letters too, the code with handle it
        List<String> doctorList = doctorService.getAllDoctorsWithAgeMoreThanX(gender, age);
        return new ResponseEntity<>(doctorList, HttpStatus.FOUND);
    }

    // get the ratio of male to female doctors
    @GetMapping("/get-male-female-ratio")
    public ResponseEntity getDoctorsMaleToFemaleRatio() throws ArithmeticException {

        double ratio = doctorService.getDoctorsMaleToFemaleRatio();
        return new ResponseEntity<>(ratio, HttpStatus.FOUND);
    }

    // PUT
    // Update details based on id of the doctor
    @PutMapping("/update-details")
    public ResponseEntity updateDoctorDetailsById(@RequestParam(value = "id") Integer id,
                                                    @RequestBody DoctorRequestDto doctorRequestDto) {

        DoctorResponseDto doctorResponseDto;

        doctorResponseDto = doctorService.updateDoctor(id, doctorRequestDto);
        return new ResponseEntity<>(doctorResponseDto, HttpStatus.CREATED);
    }


    // DELETE

    // Delete a doctor by id
    @DeleteMapping("/delete/id/{id}")
    public String deleteDoctorById(@PathVariable Integer id) {

        return doctorService.deleteDoctorById(id);
    }
}
