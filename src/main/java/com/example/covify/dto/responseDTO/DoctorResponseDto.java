package com.example.covify.dto.responseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DoctorResponseDto {

    String name;

    String emailId;

    String mobileNo;

    CenterResponseDto centerResponseDto;

}
