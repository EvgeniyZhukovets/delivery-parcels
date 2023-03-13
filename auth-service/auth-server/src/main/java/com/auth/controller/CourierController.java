package com.auth.controller;

import com.auth.dto.response.GetAllCouriersResponse;
import com.auth.service.CourierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Log4j2
public class CourierController {

    private final CourierService service;

    @GetMapping("/couriers")
    public GetAllCouriersResponse getAllCouriers() {
        log.info("getAllCouriers called");
        return service.getAllCouriers();
    }
}
