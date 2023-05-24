package com.example.covify.service.impl;

import com.example.covify.dto.requestDTO.UserRequestDto;
import com.example.covify.dto.responseDTO.UserResponseDto;
import com.example.covify.model.User;
import com.example.covify.repository.UserRepository;
import com.example.covify.service.UserService;
import com.example.covify.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;


    @Override
    public UserResponseDto addUser(UserRequestDto userRequest) {

        // change dto - entity
//        User user = new User();
//        // set required info
//        user.setAge(userRequest.getAge());
//        user.setGender(userRequest.getGender());
//        user.setName(userRequest.getName());
//        user.setEmailId(userRequest.getEmailId());
//        user.setMobileNo(userRequest.getMobileNo());
        User user = UserTransformer.userRequestDtoToUser(userRequest);

        // create new user, it will return user entity
        User savedUser = userRepository.save(user);

        // create new user response dto
//        UserResponse userResponse = new UserResponse();

        UserResponseDto userResponse = UserTransformer.userToUserResponseDTO(savedUser, "The user is successfully added");

        return userResponse;
    }


    @Override
    public UserResponseDto getUserById(Integer id) {

        // get user by id
        User user = userRepository.findById(id).get();

        // create new user response dto
//        UserResponse userResponse = new UserResponse();

        // send only required info in dto
        UserResponseDto userResponse = UserTransformer.userToUserResponseDTO(user, "Your account exists in our records");

        return userResponse;
    }


    // API - 3 ---> get all users
    @Override
    public List<UserResponseDto> getAllUsers() {

        // get list of all user entities
        List<User> userList = userRepository.findAll();

        // answer arrayList
        return makeUserResponseListFromUserList(userList);
    }

    @Override
    public UserResponseDto getUserByEmailId(String emailId) {

        // get user entity
        User user = userRepository.findByEmailId(emailId);

        // create new response dto and return it
//        UserResponse userResponse = new UserResponse();
        UserResponseDto userResponse = UserTransformer.userToUserResponseDTO(user, "This data was found by your email id");

        return userResponse;
    }

    @Override
    public UserResponseDto updateUserMobileNo(Integer id, String mobileNo) {

        // get user entity
        User user = userRepository.findById(id).get();

        // update mobile no
        user.setMobileNo(mobileNo);

        // put this into database
        User updatedUser = userRepository.save(user);

        // create new response dto and return it
//        UserResponse userResponse = new UserResponse();
        UserResponseDto userResponse = UserTransformer.userToUserResponseDTO(updatedUser, "The mobile no has been updated");

        return userResponse;
    }

    @Override
    public List<UserResponseDto> getAllUsersWhoHaveNotTakenAnyDose() {

        List<User> userList = userRepository.getAllUsersWhoHaveNotTakenAnyDose();

        return makeUserResponseListFromUserList(userList);
    }

    @Override
    public List<UserResponseDto> getAllUsersWhoHaveNotTakenDose1ButNotDose2() {

        List<User> userList = userRepository.getAllUsersWhoHaveNotTakenDose1ButNotDose2();

        return makeUserResponseListFromUserList(userList);
    }

    @Override
    public List<UserResponseDto> getAllUsersWhoAreFullyVaccinated() {

        List<User> userList = userRepository.getAllUsersWhoAreFullyVaccinated();

        return makeUserResponseListFromUserList(userList);
    }

    @Override
    public List<UserResponseDto> getAllMalesWhoHaveNotTakenAnyDose() {

        List<User> userList = userRepository.getAllMalesWhoHaveNotTakenAnyDose();

        return makeUserResponseListFromUserList(userList);
    }

    @Override
    public List<UserResponseDto> getAllFemalesWhoAreFullyVaccinated() {

        List<User> userList = userRepository.getAllFemalesWhoAreFullyVaccinated();

        return makeUserResponseListFromUserList(userList);
    }


    public List<UserResponseDto> makeUserResponseListFromUserList(List<User> userList) {

        List<UserResponseDto> userResponseList = new ArrayList<>();

        // make response dto list out of it and return
        for (User user : userList) {
            // make new user response dto
//            UserResponse userResponse = new UserResponse();
            // update info in response dto
            UserResponseDto userResponse = UserTransformer.userToUserResponseDTO(user, "");
            // add it in ans list
            userResponseList.add(userResponse);
        }

        return userResponseList;
    }



}
