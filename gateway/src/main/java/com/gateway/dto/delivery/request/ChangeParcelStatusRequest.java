package com.gateway.dto.delivery.request;

public record ChangeParcelStatusRequest(Long parcelId, String parcelStatus) {
}
