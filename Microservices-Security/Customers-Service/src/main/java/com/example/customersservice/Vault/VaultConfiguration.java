package com.example.customersservice.Vault;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.annotation.VaultPropertySource;

import java.security.interfaces.RSAPrivateKey;

@Data
@Configuration
@ConfigurationProperties("demo")
public class VaultConfiguration {
private RSAPrivateKey privatekey;
}