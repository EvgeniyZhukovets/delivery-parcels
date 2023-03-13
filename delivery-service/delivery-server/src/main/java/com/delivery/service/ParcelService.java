package com.delivery.service;

import com.delivery.dto.request.*;
import com.delivery.dto.response.GetParcelCoordinatesResponse;
import com.delivery.dto.response.GetParcelResponse;
import com.delivery.dto.response.GetParcelsByCourierResponse;
import com.delivery.event.CreateOrderEvent;

public interface ParcelService {

    void assignCourier(AssignToCourierRequest request);

    void createParcel(CreateOrderEvent event);

    void cancelParcel(Long orderId);

    void changeDestination(ChangeDestinationRequest request);

    void changeParcelStatusForCouriers(ChangeParcelStatusRequest request);

    GetParcelResponse getParcel(GetParcelByIdRequest request);

    GetParcelResponse getParcelByOrderId(GetParcelByOrderIdRequest request);

    GetParcelCoordinatesResponse getParcelCoordinates(GetParcelByIdRequest request);

    void changeParcelCoordinates(ChangeParcelCoordinatesRequest request);

    GetParcelsByCourierResponse getParcelsByCourier(GetParcelsByCourierRequest request);

    GetParcelResponse getParcelByCourier(GetParcelsByCourierRequest request);
}
