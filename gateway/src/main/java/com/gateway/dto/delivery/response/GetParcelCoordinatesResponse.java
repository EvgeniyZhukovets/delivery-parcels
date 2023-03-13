package com.gateway.dto.delivery.response;

import com.common.ResponseInfo;

public record GetParcelCoordinatesResponse(Float latitude, Float longitude, ResponseInfo responseInfo) {
}
