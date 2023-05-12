package com.example.covify.controller;

import com.example.covify.dto.requestDto.UserRequest;
import com.example.covify.dto.responseDto.UserResponse;
import com.example.covify.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;



    // API - 1 ---> add a user
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody UserRequest userRequest) {

        // send user_request_dto to service layer
        // service layer will return user_response_dto

        UserResponse userSaved = userService.addUser(userRequest);

        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }


    // API - 2 ---> get user by id
    @GetMapping("/get/{id}")
    public ResponseEntity getUserById(@PathVariable Integer id) {

        // pass if to service layer
        // it will return user response dto
        // return it

        UserResponse userResponse = userService.getUserById(id);

        return new ResponseEntity<>(userResponse, HttpStatus.FOUND);
    }


    // API - 3 ---> get all users
    @GetMapping("/get/all")
    public ResponseEntity getAllUsers() {

        List<UserResponse> userResponseList = userService.getAllUsers();

        return new ResponseEntity<>(userResponseList, HttpStatus.FOUND);
    }

    // API - 4 ---> find the user by Email id
    @GetMapping("/get/email")
    public ResponseEntity getByEmailId(@RequestParam("emailId") String emailId) {

        UserResponse userResponse = userService.getUserByEmailId(emailId);

        return new ResponseEntity<>(userResponse, HttpStatus.FOUND);
    }



    // API - 5 ---> update the name of the user mobile no
    @PutMapping("/update/mobile")
    public ResponseEntity updateMobileNo(@RequestParam("id") Integer id,
                                         @RequestParam("mobileNo") String mobileNo) {

        UserResponse userResponse = userService.updateUserMobileNo(id, mobileNo);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }


    // API - 6 ---> get all the users who have not taken even the first dose
    @GetMapping("/get/all/not_taken_any_dose")
    public ResponseEntity getAllUsersWhoHaveNotTakenAnyDose() {

        List<UserResponse> userResponseList = userService.getAllUsersWhoHaveNotTakenAnyDose();

        return new ResponseEntity<>(userResponseList, HttpStatus.FOUND);
    }


    // API - 7 ---> get all the users who have taken dose 1 but not dose 2
    @GetMapping("/get/all/taken_dose1_but_not_dose2")
    public ResponseEntity getAllUsersWhoHaveNotTakenDose1ButNotDose2() {

        List<UserResponse> userResponseList = userService.getAllUsersWhoHaveNotTakenDose1ButNotDose2();

        return new ResponseEntity<>(userResponseList, HttpStatus.FOUND);
    }


    // API - 8 ---> get all the users who are fully vaccinated
    @GetMapping("/get/all/fully_vaccinated")
    public ResponseEntity getAllUsersWhoAreFullyVaccinated() {

        List<UserResponse> userResponseList = userService.getAllUsersWhoAreFullyVaccinated();

        return new ResponseEntity<>(userResponseList, HttpStatus.FOUND);
    }

    // API - 9 ---> get all the male users who have not taken any vaccine
    @GetMapping("/get/males/not_taken_any_dose")
    public ResponseEntity getAllMalesWhoHaveNotTakenAnyDose() {

        List<UserResponse> userResponseList = userService.getAllMalesWhoHaveNotTakenAnyDose();

        return new ResponseEntity<>(userResponseList, HttpStatus.FOUND);
    }

    // API - 10 ---> get all the female users who are fully vaccinated
    @GetMapping("/get/females/fully_vaccinated")
    public ResponseEntity getAllFemalesWhoAreFullyVaccinated() {

        List<UserResponse> userResponseList = userService.getAllFemalesWhoAreFullyVaccinated();

        return new ResponseEntity<>(userResponseList, HttpStatus.FOUND);
    }

}
