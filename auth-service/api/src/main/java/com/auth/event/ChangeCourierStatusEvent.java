package com.auth.event;

import com.auth.enums.CourierStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeCourierStatusEvent implements Serializable {

    private Long courierId;
    private CourierStatus courierStatus;
}
