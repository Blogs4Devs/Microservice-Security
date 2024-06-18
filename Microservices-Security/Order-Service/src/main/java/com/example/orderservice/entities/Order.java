package com.example.orderservice.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "\"order\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
private int price ;
private Long idCustomer;

@OneToMany(mappedBy = "order")
    private List<Product> products;
}
