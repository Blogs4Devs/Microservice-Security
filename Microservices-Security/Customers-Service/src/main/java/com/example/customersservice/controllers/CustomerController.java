package com.example.customersservice.controllers;

import com.example.customersservice.Vault.VaultConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.interfaces.RSAPrivateKey;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
@Transactional
@CrossOrigin("*")
public class CustomerController {

    @GetMapping
    public String getcustomer(){
        return "Customer Service";
    }


    @Autowired
    private VaultConfiguration vaultConfiguration;

    @GetMapping("/getDataFromVault")
    public void getDataFromVault() {
        System.out.println(vaultConfiguration.getPrivatekey()) ;
    }
}

