package com.example.covify.transformer;

import com.example.covify.dto.requestDTO.UserRequestDto;
import com.example.covify.dto.responseDTO.UserResponseDto;
import com.example.covify.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserTransformer {


    public static User userRequestDtoToUser(UserRequestDto userRequest) {
        return User.builder()
                .age(userRequest.getAge())
                .gender(userRequest.getGender())
                .emailId(userRequest.getEmailId())
                .mobileNo(userRequest.getMobileNo())
                .name(userRequest.getName())
                .build();
    }
    public static UserResponseDto userToUserResponseDTO(User user, String message) {

//        userResponse.setName(user.getName());
//        userResponse.setAge(user.getAge());
//        userResponse.setGender(user.getGender());
//        userResponse.setMobileNo(user.getMobileNo());
//        userResponse.setEmailId(user.getEmailId());
//        userResponse.setMessage(message);

        return UserResponseDto.builder()
                .age(user.getAge())
                .name(user.getName())
                .gender(user.getGender())
                .emailId(user.getEmailId())
                .message(message)
                .mobileNo(user.getMobileNo())
                .build();
    }

}
