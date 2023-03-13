package com.gateway.clients;


import com.common.StandardResponse;
import com.gateway.dto.delivery.request.*;
import com.gateway.dto.delivery.response.GetParcelCoordinatesResponse;
import com.gateway.dto.delivery.response.GetParcelResponse;
import com.gateway.dto.delivery.response.GetParcelsByCourierResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "deliveryFeignClient", url = "${delivery.delivery.address}")
public interface DeliveryFeignClient {

    @PostMapping("/parcel/assign-courier")
    StandardResponse assignCourier(@RequestBody AssignToCourierRequest request);

    @PutMapping("/change-destination")
    StandardResponse changeDestination(@RequestBody ChangeDestinationRequest request);

    @PutMapping("/change-parcel-status-for-couriers")
    StandardResponse changeParcelStatusForCouriers(@RequestBody ChangeParcelStatusRequest request);

    @GetMapping("/by-id")
    GetParcelResponse getParcelById(@RequestBody GetParcelByIdRequest request);

    @GetMapping("/by-order-id")
    GetParcelResponse getParcelByOrderId(@RequestBody GetParcelByOrderIdRequest request);

    @GetMapping("/coordinates")
    GetParcelCoordinatesResponse getParcelCoordinates(@RequestBody GetParcelByIdRequest request);

    @PutMapping("/coordinates")
    StandardResponse changeParcelCoordinates(@RequestBody ChangeParcelCoordinatesRequest request);

    @GetMapping("/all-by-courier-id")
    GetParcelsByCourierResponse getParcelsByCourierId(@RequestBody GetParcelsByCourierRequest request);

    @GetMapping("/by-courier-id")
    GetParcelResponse getParcelByCourierId(@RequestBody GetParcelsByCourierRequest request);
}
