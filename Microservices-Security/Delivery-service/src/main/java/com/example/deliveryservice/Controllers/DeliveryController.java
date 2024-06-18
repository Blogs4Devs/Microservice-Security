package com.example.deliveryservice.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/deliveries")
@AllArgsConstructor
@Transactional
@CrossOrigin("*")
public class DeliveryController {

    @GetMapping
    public String getDelivery(){
        return "Delivery Service";
    }
}



