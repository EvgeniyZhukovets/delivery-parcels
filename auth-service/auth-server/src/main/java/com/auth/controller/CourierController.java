package com.auth.controller;

import com.auth.dto.response.GetAllCouriersResponse;
import com.auth.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CourierController {

    private final CourierService service;

    @GetMapping("/couriers")
    public GetAllCouriersResponse getAllCouriers(){
        return service.getAllCouriers();
    }
}
