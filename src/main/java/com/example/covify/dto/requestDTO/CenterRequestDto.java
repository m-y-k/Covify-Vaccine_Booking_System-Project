package com.example.covify.dto.requestDTO;

import com.example.covify.enums.CenterType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CenterRequestDto {

    String name;

    String location;

    CenterType centerType;

}
