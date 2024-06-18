package com.example.orderservice.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "Delivery-service",url = "http://localhost:8083"  )
public interface DeliveryClient {
    @GetMapping("/deliveries")
    public String getDeliveries(@RequestHeader("Authorization") String accessToken);


}
