package com.delivery.controller;

import com.common.StandardResponse;
import com.delivery.dto.request.*;
import com.delivery.dto.response.GetParcelCoordinatesResponse;
import com.delivery.dto.response.GetParcelResponse;
import com.delivery.dto.response.GetParcelsByCourierResponse;
import com.delivery.service.ParcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parcel")
@RequiredArgsConstructor
public class ParcelController {

    private final ParcelService service;

    @PostMapping("/assign-courier")
    public StandardResponse assignCourier(@RequestBody AssignToCourierRequest request) {
        service.assignCourier(request);
        return new StandardResponse();
    }

    @PutMapping("/change-destination")
    public StandardResponse changeDestination(@RequestBody ChangeDestinationRequest request) {
        service.changeDestination(request);
        return new StandardResponse();
    }

    @PutMapping("/change-parcel-status-for-couriers")
    public StandardResponse changeParcelStatusForCouriers(@RequestBody ChangeParcelStatusRequest request) {
        service.changeParcelStatusForCouriers(request);
        return new StandardResponse();
    }

    @GetMapping("/by-id")
    public GetParcelResponse getParcelById(@RequestBody GetParcelByIdRequest request) {
        return service.getParcel(request);
    }

    @GetMapping("/by-order-id")
    public GetParcelResponse getParcelByOrderId(@RequestBody GetParcelByOrderIdRequest request) {
        return service.getParcelByOrderId(request);
    }

    @GetMapping("/coordinates")
    public GetParcelCoordinatesResponse getParcelCoordinates(@RequestBody GetParcelByIdRequest request) {
        return service.getParcelCoordinates(request);
    }

    @PutMapping("/coordinates")
    public StandardResponse changeParcelCoordinates(@RequestBody ChangeParcelCoordinatesRequest request) {
        service.changeParcelCoordinates(request);
        return new StandardResponse();
    }

    @GetMapping("/all-by-courier-id")
    public GetParcelsByCourierResponse getParcelsByCourierId(@RequestBody GetParcelsByCourierRequest request) {
        return service.getParcelsByCourier(request);
    }

    @GetMapping("/by-courier-id")
    public GetParcelResponse getParcelByCourierId(@RequestBody GetParcelsByCourierRequest request) {
        return service.getParcelByCourier(request);
    }
}
