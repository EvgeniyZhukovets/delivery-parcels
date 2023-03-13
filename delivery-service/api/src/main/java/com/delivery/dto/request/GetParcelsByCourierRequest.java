package com.delivery.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetParcelsByCourierRequest {

    @NotNull
    private Long courierId;
}
