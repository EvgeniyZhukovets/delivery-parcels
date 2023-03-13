package com.gateway.clients;


import com.gateway.dto.auth.response.GetAllCouriersResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "courierAuthFeignClient", url = "${delivery.auth.address}")
public interface CourierAuthFeignClient {

    @GetMapping("/couriers")
    GetAllCouriersResponse getAllCouriers();
}
