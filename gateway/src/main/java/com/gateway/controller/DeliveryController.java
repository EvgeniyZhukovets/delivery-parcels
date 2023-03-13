package com.gateway.controller;

import com.common.StandardResponse;
import com.gateway.clients.DeliveryFeignClient;
import com.gateway.dto.delivery.request.*;
import com.gateway.dto.delivery.response.GetParcelCoordinatesResponse;
import com.gateway.dto.delivery.response.GetParcelResponse;
import com.gateway.dto.delivery.response.GetParcelsByCourierResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parcel")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryFeignClient feignClient;

    @PostMapping("/assign-courier")
    public StandardResponse assignCourier(@RequestBody AssignToCourierRequest request) {
        return feignClient.assignCourier(request);
    }

    @PutMapping("/change-destination")
    public StandardResponse changeDestination(@RequestBody ChangeDestinationRequest request) {
        return feignClient.changeDestination(request);
    }

    @PutMapping("/change-parcel-status-for-couriers")
    public StandardResponse changeParcelStatusForCouriers(@RequestBody ChangeParcelStatusRequest request) {
        return feignClient.changeParcelStatusForCouriers(request);
    }

    @GetMapping("/by-id")
    public GetParcelResponse getParcelById(@RequestBody GetParcelByIdRequest request) {
        return feignClient.getParcelById(request);
    }

    @GetMapping("/by-order-id")
    public GetParcelResponse getParcelByOrderId(@RequestBody GetParcelByOrderIdRequest request) {
        return feignClient.getParcelByOrderId(request);
    }

    @GetMapping("/coordinates")
    public GetParcelCoordinatesResponse getParcelCoordinates(@RequestBody GetParcelByIdRequest request) {
        return feignClient.getParcelCoordinates(request);
    }

    @PutMapping("/coordinates")
    public StandardResponse changeParcelCoordinates(@RequestBody ChangeParcelCoordinatesRequest request) {
        return feignClient.changeParcelCoordinates(request);
    }

    @GetMapping("/all-by-courier-id")
    public GetParcelsByCourierResponse getParcelsByCourierId(@RequestBody GetParcelsByCourierRequest request) {
        return feignClient.getParcelsByCourierId(request);
    }

    @GetMapping("/by-courier-id")
    public GetParcelResponse getParcelByCourierId(@RequestBody GetParcelsByCourierRequest request) {
        return feignClient.getParcelByCourierId(request);
    }
}
