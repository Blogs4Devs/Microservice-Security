package com.example.deliveryservice.Services;

import com.example.deliveryservice.repositories.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;



}
