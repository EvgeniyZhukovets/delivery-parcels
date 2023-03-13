package com.delivery.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AssignToCourierRequest {

    @NotNull
    private Long parcelId;
    @NotNull
    private Long courierId;
}
