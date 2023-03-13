package com.gateway.dto.delivery.response;

import com.common.ResponseInfo;
import com.gateway.dto.delivery.common.ParcelDto;

import java.util.List;

public record GetParcelsByCourierResponse(List<ParcelDto> parcelDtos, ResponseInfo responseInfo) {
}
