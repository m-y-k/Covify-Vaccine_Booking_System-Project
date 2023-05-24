package com.example.covify.service;

import com.example.covify.dto.requestDTO.UserRequestDto;
import com.example.covify.dto.responseDTO.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {


    UserResponseDto addUser(UserRequestDto userRequest);

    UserResponseDto getUserById(Integer id);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserByEmailId(String emailId);

    UserResponseDto updateUserMobileNo(Integer id, String mobileNo);

    List<UserResponseDto> getAllUsersWhoHaveNotTakenAnyDose();

    List<UserResponseDto> getAllUsersWhoHaveNotTakenDose1ButNotDose2();

    List<UserResponseDto> getAllUsersWhoAreFullyVaccinated();

    List<UserResponseDto> getAllMalesWhoHaveNotTakenAnyDose();

    List<UserResponseDto> getAllFemalesWhoAreFullyVaccinated();

}
