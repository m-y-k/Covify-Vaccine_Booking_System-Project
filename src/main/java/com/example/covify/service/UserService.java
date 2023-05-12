package com.example.covify.service;

import com.example.covify.dto.requestDto.UserRequest;
import com.example.covify.dto.responseDto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {


    UserResponse addUser(UserRequest userRequest);

    UserResponse getUserById(Integer id);

    List<UserResponse> getAllUsers();

    UserResponse getUserByEmailId(String emailId);

    UserResponse updateUserMobileNo(Integer id, String mobileNo);

    List<UserResponse> getAllUsersWhoHaveNotTakenAnyDose();

    List<UserResponse> getAllUsersWhoHaveNotTakenDose1ButNotDose2();

    List<UserResponse> getAllUsersWhoAreFullyVaccinated();

    List<UserResponse> getAllMalesWhoHaveNotTakenAnyDose();

    List<UserResponse> getAllFemalesWhoAreFullyVaccinated();

}
