package com.gateway.dto.delivery.response;

import com.common.ResponseInfo;
import com.gateway.dto.delivery.common.ParcelDto;

public record GetParcelResponse(ParcelDto parcelDto, ResponseInfo responseInfo) {
}
