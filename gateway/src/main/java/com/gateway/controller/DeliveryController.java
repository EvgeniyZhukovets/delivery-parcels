package com.gateway.controller;

import com.common.StandardResponse;
import com.gateway.clients.DeliveryFeignClient;
import com.gateway.dto.delivery.request.*;
import com.gateway.dto.delivery.response.GetParcelCoordinatesResponse;
import com.gateway.dto.delivery.response.GetParcelResponse;
import com.gateway.dto.delivery.response.GetParcelsByCourierResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parcel")
@RequiredArgsConstructor
@Log4j2
public class DeliveryController {

    private final DeliveryFeignClient feignClient;

    @PostMapping("/assign-courier")
    public StandardResponse assignCourier(@RequestBody AssignToCourierRequest request) {
        log.info("assignCourier called");
        return feignClient.assignCourier(request);
    }

    @PutMapping("/change-destination")
    public StandardResponse changeDestination(@RequestBody ChangeDestinationRequest request) {
        log.info("changeDestination called");
        return feignClient.changeDestination(request);
    }

    @PutMapping("/change-parcel-status-for-couriers")
    public StandardResponse changeParcelStatusForCouriers(@RequestBody ChangeParcelStatusRequest request) {
        log.info("changeParcelStatusForCouriers called");
        return feignClient.changeParcelStatusForCouriers(request);
    }

    @GetMapping("/by-id")
    public GetParcelResponse getParcelById(@RequestBody GetParcelByIdRequest request) {
        log.info("getParcelById called");
        return feignClient.getParcelById(request);
    }

    @GetMapping("/by-order-id")
    public GetParcelResponse getParcelByOrderId(@RequestBody GetParcelByOrderIdRequest request) {
        log.info("getParcelByOrderId called");
        return feignClient.getParcelByOrderId(request);
    }

    @GetMapping("/coordinates")
    public GetParcelCoordinatesResponse getParcelCoordinates(@RequestBody GetParcelByIdRequest request) {
        log.info("getParcelCoordinates called");
        return feignClient.getParcelCoordinates(request);
    }

    @PutMapping("/coordinates")
    public StandardResponse changeParcelCoordinates(@RequestBody ChangeParcelCoordinatesRequest request) {
        log.info("changeParcelCoordinates called");
        return feignClient.changeParcelCoordinates(request);
    }

    @GetMapping("/all-by-courier-id")
    public GetParcelsByCourierResponse getParcelsByCourierId(@RequestBody GetParcelsByCourierRequest request) {
        log.info("getParcelsByCourierId called");
        return feignClient.getParcelsByCourierId(request);
    }

    @GetMapping("/by-courier-id")
    public GetParcelResponse getParcelByCourierId(@RequestBody GetParcelsByCourierRequest request) {
        log.info("getParcelByCourierId called");
        return feignClient.getParcelByCourierId(request);
    }
}
