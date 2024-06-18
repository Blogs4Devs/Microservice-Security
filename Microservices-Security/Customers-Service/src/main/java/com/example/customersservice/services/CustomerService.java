package com.example.customersservice.services;

import com.example.customersservice.entities.Customer;
import com.example.customersservice.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    public List<Customer> getUsers() {
        return customerRepository.findAll();
    }

    public Customer createUser(Customer user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return customerRepository.save(user);
    }

    public Customer updateUser(Customer user) {
        return customerRepository.saveAndFlush(user);
    }





    public Page<Customer> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepository.findAll(pageable);
    }

    public Customer loadUserByEmail(String email) {
        return customerRepository.findByEmail(email);

    }
}
