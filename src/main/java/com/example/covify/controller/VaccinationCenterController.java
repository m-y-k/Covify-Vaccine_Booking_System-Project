package com.example.covify.controller;

import com.example.covify.dto.requestDTO.CenterRequestDto;
import com.example.covify.dto.responseDTO.CenterResponseDto;
import com.example.covify.dto.responseDTO.DoctorResponseDto;
import com.example.covify.service.impl.CenterServiceImpl;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/center")
public class VaccinationCenterController {

    @Autowired
    CenterServiceImpl centerService;

    // POST

    // add a vaccination center
    @PostMapping("/add")
    public ResponseEntity addVaccinationCenter(@RequestBody CenterRequestDto centerRequestDto) {

        CenterResponseDto centerResponseDto = centerService.addCenter(centerRequestDto);
        return new ResponseEntity<>(centerResponseDto, HttpStatus.CREATED);
    }


    // GET

    // get the list of all the doctors at a particular center{center_id}
    @GetMapping("/get-all-doctors/center/{id}")
    public ResponseEntity getAllDoctorsAtCenter (@PathVariable Integer id) {

        List<DoctorResponseDto> doctorList = centerService.getAllDoctorsAtCenter(id);
        return new ResponseEntity<>(doctorList, HttpStatus.FOUND);
    }


    // get the list of all the {gender} doctors at a particular center{gender}/{center_id}
    @GetMapping("/get-all-doctors/gender/{gender}/center/{center_id}")
    public ResponseEntity getAllGenderDoctorsAtCenter (@PathVariable String gender,
                                                       @PathVariable Integer id) {

        List<DoctorResponseDto> doctorList = centerService.getAllGenderDoctorsAtCenter(gender, id);
        return new ResponseEntity<>(doctorList, HttpStatus.FOUND);
    }


    // get the list of all the {gender} doctors of age > x at a particular center{gender}/{center_id}/{age}
    @GetMapping("/get-all-doctors/gender/{gender}/center/{center_id}/age/{age}")
    public ResponseEntity getAllGenderDoctorsAtCenterAgeAboveX (@PathVariable String gender,
                                                                @PathVariable Integer age,
                                                                @PathVariable Integer id) {

        List<DoctorResponseDto> doctorList = centerService.getAllGenderDoctorsAtCenterAgeAboveX(gender, age, id);
        return new ResponseEntity<>(doctorList, HttpStatus.FOUND);
    }


    // give list of all the centers of a particular center type
    @GetMapping("/get-all-centers-of-type/center/{center_id}")
    public ResponseEntity getAllCentersAsCenter (@PathVariable Integer id) {

        List<CenterResponseDto> centerList = centerService.getAllCentersAsCenter(id);
        return new ResponseEntity<>(centerList, HttpStatus.FOUND);
    }

}
