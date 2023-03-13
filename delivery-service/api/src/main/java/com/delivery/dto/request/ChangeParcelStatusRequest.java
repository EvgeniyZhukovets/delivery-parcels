package com.delivery.dto.request;

import com.delivery.enums.ParcelStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ChangeParcelStatusRequest {

    @NotNull
    private Long parcelId;
    @NotNull
    private ParcelStatus parcelStatus;
}
