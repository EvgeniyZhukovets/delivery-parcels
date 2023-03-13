package com.delivery.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ChangeParcelCoordinatesRequest {

    @NotNull
    private Long parcelId;
    @NotNull
    private Float latitude;
    @NotNull
    private Float longitude;
}
