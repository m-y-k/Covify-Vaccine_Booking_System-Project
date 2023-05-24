package com.example.covify.dto.responseDTO;

import com.example.covify.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResponseDto {

    String name;

    int age;

    String emailId;

    String mobileNo;

    Gender gender;

    String message;

}
