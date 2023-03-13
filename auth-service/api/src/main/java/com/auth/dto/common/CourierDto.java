package com.auth.dto.common;

import com.auth.enums.CourierStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourierDto {

    private String username;
    private CourierStatus courierStatus;
}
