package com.delivery.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetParcelByIdRequest {

    @NotNull
    private Long parcelId;
}
