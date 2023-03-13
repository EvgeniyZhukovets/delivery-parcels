package com.delivery.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ChangeDestinationRequest {

    @NotNull
    private Long parcelId;
    @NotBlank
    private String destination;
}
