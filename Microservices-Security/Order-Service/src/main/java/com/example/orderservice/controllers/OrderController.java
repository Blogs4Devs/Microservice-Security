package com.example.orderservice.controllers;

import com.example.orderservice.Clients.DeliveryClient;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
@Transactional
@CrossOrigin("*")
public class OrderController {
    private final DeliveryClient deliveryClient;

    @GetMapping
    public String getorder() {
        return "Order Service";
    }

    @GetMapping("/deliveries")
    public String getdelivery(@RequestHeader("Authorization") String accessToken) {
        return deliveryClient.getDeliveries(accessToken);
    }
}
