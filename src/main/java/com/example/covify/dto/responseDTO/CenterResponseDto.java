package com.example.covify.dto.responseDTO;

import com.example.covify.enums.CenterType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CenterResponseDto {

    String name;

    String location;

    CenterType centerType;
}
