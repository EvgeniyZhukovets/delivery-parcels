package com.gateway.controller;

import com.gateway.clients.CourierAuthFeignClient;
import com.gateway.dto.auth.response.GetAllCouriersResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class CourierController {

    private final CourierAuthFeignClient feignClient;

    @GetMapping("/couriers")
    public GetAllCouriersResponse getAllCouriers(){
        log.info("getAllCouriers called");
        return feignClient.getAllCouriers();
    }
}
