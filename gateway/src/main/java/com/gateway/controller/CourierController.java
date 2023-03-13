package com.gateway.controller;

import com.gateway.clients.CourierAuthFeignClient;
import com.gateway.dto.auth.response.GetAllCouriersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CourierController {

    private final CourierAuthFeignClient feignClient;

    @GetMapping("/couriers")
    public GetAllCouriersResponse getAllCouriers(){
        return feignClient.getAllCouriers();
    }
}
