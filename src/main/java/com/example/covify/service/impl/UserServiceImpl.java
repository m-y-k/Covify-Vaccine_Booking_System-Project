package com.example.covify.service.impl;

import com.example.covify.dto.requestDto.UserRequest;
import com.example.covify.dto.responseDto.UserResponse;
import com.example.covify.model.User;
import com.example.covify.repository.UserRepository;
import com.example.covify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;


    @Override
    public UserResponse addUser(UserRequest userRequest) {

        // change dto - entity
        User user = new User();
        // set required info
        user.setAge(userRequest.getAge());
        user.setGender(userRequest.getGender());
        user.setName(userRequest.getName());
        user.setEmailId(userRequest.getEmailId());
        user.setMobileNo(userRequest.getMobileNo());

        // create new user, it will return user entity
        User savedUser = userRepository.save(user);

        // create new user response dto
        UserResponse userResponse = new UserResponse();

        updateUserResponse(userResponse, savedUser, "The user is successfully added");

        return userResponse;
    }


    @Override
    public UserResponse getUserById(Integer id) {

        // get user by id
        User user = userRepository.findById(id).get();

        // create new user response dto
        UserResponse userResponse = new UserResponse();

        // send only required info in dto
        updateUserResponse(userResponse, user, "Your account exists in our records");

        return userResponse;
    }


    // API - 3 ---> get all users
    @Override
    public List<UserResponse> getAllUsers() {

        // get list of all user entities
        List<User> userList = userRepository.findAll();

        // answer arrayList
        return makeUserResponseListFromUserList(userList);
    }

    @Override
    public UserResponse getUserByEmailId(String emailId) {

        // get user entity
        User user = userRepository.findByEmailId(emailId);

        // create new response dto and return it
        UserResponse userResponse = new UserResponse();
        updateUserResponse(userResponse, user, "This data was found by your email id");

        return userResponse;
    }

    @Override
    public UserResponse updateUserMobileNo(Integer id, String mobileNo) {

        // get user entity
        User user = userRepository.findById(id).get();

        // update mobile no
        user.setMobileNo(mobileNo);

        // put this into database
        User updatedUser = userRepository.save(user);

        // create new response dto and return it
        UserResponse userResponse = new UserResponse();
        updateUserResponse(userResponse, updatedUser, "The mobile no has been updated");

        return userResponse;
    }

    @Override
    public List<UserResponse> getAllUsersWhoHaveNotTakenAnyDose() {

        List<User> userList = userRepository.getAllUsersWhoHaveNotTakenAnyDose();

        return makeUserResponseListFromUserList(userList);
    }

    @Override
    public List<UserResponse> getAllUsersWhoHaveNotTakenDose1ButNotDose2() {

        List<User> userList = userRepository.getAllUsersWhoHaveNotTakenDose1ButNotDose2();

        return makeUserResponseListFromUserList(userList);
    }

    @Override
    public List<UserResponse> getAllUsersWhoAreFullyVaccinated() {

        List<User> userList = userRepository.getAllUsersWhoAreFullyVaccinated();

        return makeUserResponseListFromUserList(userList);
    }

    @Override
    public List<UserResponse> getAllMalesWhoHaveNotTakenAnyDose() {

        List<User> userList = userRepository.getAllMalesWhoHaveNotTakenAnyDose();

        return makeUserResponseListFromUserList(userList);
    }

    @Override
    public List<UserResponse> getAllFemalesWhoAreFullyVaccinated() {

        List<User> userList = userRepository.getAllFemalesWhoAreFullyVaccinated();

        return makeUserResponseListFromUserList(userList);
    }


    public List<UserResponse> makeUserResponseListFromUserList(List<User> userList) {

        List<UserResponse> userResponseList = new ArrayList<>();

        // make response dto list out of it and return
        for (User user : userList) {
            // make new user response dto
            UserResponse userResponse = new UserResponse();
            // update info in response dto
            updateUserResponse(userResponse, user, "");
            // add it in ans list
            userResponseList.add(userResponse);
        }

        return userResponseList;
    }

    public void updateUserResponse(UserResponse userResponse, User user, String message) {

        userResponse.setName(user.getName());
        userResponse.setAge(user.getAge());
        userResponse.setGender(user.getGender());
        userResponse.setMobileNo(user.getMobileNo());
        userResponse.setEmailId(user.getEmailId());
        userResponse.setMessage(message);
    }

}
