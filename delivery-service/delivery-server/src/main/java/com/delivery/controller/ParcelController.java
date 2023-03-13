package com.delivery.controller;

import com.common.StandardResponse;
import com.delivery.dto.request.*;
import com.delivery.dto.response.GetParcelCoordinatesResponse;
import com.delivery.dto.response.GetParcelResponse;
import com.delivery.dto.response.GetParcelsByCourierResponse;
import com.delivery.service.ParcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parcel")
@RequiredArgsConstructor
@Log4j2
public class ParcelController {

    private final ParcelService service;

    @PostMapping("/assign-courier")
    public StandardResponse assignCourier(@RequestBody AssignToCourierRequest request) {
        log.info("assignCourier called with request: {}", request);
        service.assignCourier(request);
        return new StandardResponse();
    }

    @PutMapping("/change-destination")
    public StandardResponse changeDestination(@RequestBody ChangeDestinationRequest request) {
        log.info("changeDestination called with request: {}", request);
        service.changeDestination(request);
        return new StandardResponse();
    }

    @PutMapping("/change-parcel-status-for-couriers")
    public StandardResponse changeParcelStatusForCouriers(@RequestBody ChangeParcelStatusRequest request) {
        log.info("changeParcelStatusForCouriers called with request: {}", request);
        service.changeParcelStatusForCouriers(request);
        return new StandardResponse();
    }

    @GetMapping("/by-id")
    public GetParcelResponse getParcelById(@RequestBody GetParcelByIdRequest request) {
        log.info("getParcelById called with request: {}", request);
        return service.getParcel(request);
    }

    @GetMapping("/by-order-id")
    public GetParcelResponse getParcelByOrderId(@RequestBody GetParcelByOrderIdRequest request) {
        log.info("getParcelByOrderId called with request: {}", request);
        return service.getParcelByOrderId(request);
    }

    @GetMapping("/coordinates")
    public GetParcelCoordinatesResponse getParcelCoordinates(@RequestBody GetParcelByIdRequest request) {
        log.info("getParcelCoordinates called with request: {}", request);
        return service.getParcelCoordinates(request);
    }

    @PutMapping("/coordinates")
    public StandardResponse changeParcelCoordinates(@RequestBody ChangeParcelCoordinatesRequest request) {
        log.info("changeParcelCoordinates called with request: {}", request);
        service.changeParcelCoordinates(request);
        return new StandardResponse();
    }

    @GetMapping("/all-by-courier-id")
    public GetParcelsByCourierResponse getParcelsByCourierId(@RequestBody GetParcelsByCourierRequest request) {
        log.info("getParcelsByCourierId called with request: {}", request);
        return service.getParcelsByCourier(request);
    }

    @GetMapping("/by-courier-id")
    public GetParcelResponse getParcelByCourierId(@RequestBody GetParcelsByCourierRequest request) {
        log.info("getParcelByCourierId called with request: {}", request);
        return service.getParcelByCourier(request);
    }
}
