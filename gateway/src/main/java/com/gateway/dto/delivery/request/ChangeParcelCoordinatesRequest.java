package com.gateway.dto.delivery.request;

public record ChangeParcelCoordinatesRequest(Long parcelId, Float latitude, Float longitude) {
}
