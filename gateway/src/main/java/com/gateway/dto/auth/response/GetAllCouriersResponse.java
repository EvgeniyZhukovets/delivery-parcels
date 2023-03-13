package com.gateway.dto.auth.response;

import com.common.ResponseInfo;
import com.gateway.dto.auth.common.CourierDto;

import java.util.List;

public record GetAllCouriersResponse(List<CourierDto> couriers, ResponseInfo responseInfo) {
}
