package com.example.customersservice.controllers;

import com.example.customersservice.entities.Customer;
        import com.example.customersservice.services.CustomerService;
        import com.example.customersservice.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {
    private JwtEncoder jwtEncoder;
    private JwtDecoder jwtDecoder;
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;



    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestParam String email,
            @RequestParam String password) {
        return loginUser(email, password, false);
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Customer user) {

        Customer existedUser = customerRepository.findByEmail(user.getEmail());
        Map<String, String> message = new HashMap<>();
        if (existedUser != null)
            return new ResponseEntity<>(message.put("message", "email already exist"), HttpStatus.CONFLICT);

        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Customer savedUser = customerRepository.save(user);

        return loginUser(user.getEmail(), password, true);
    }

    @PreAuthorize("hasAuthority('SCOPE_SIMPLE')")
    @GetMapping("/verifySimple")
    public String validate() {
        return "Simple";
    }

    @PreAuthorize("hasAuthority('SCOPE_SUPER')")

    @GetMapping("/verifySuper")
    public String validatesuper() {
    return "super";
    }

    public ResponseEntity<Map<String, Object>> loginUser(
            String email,
            String password,
            boolean firstTime) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));


        String subject = authentication.getName();
        String scope = authentication.getAuthorities()
                .stream().map(aut -> aut.getAuthority())
                .collect(Collectors.toList()).get(0);

        Map<String, Object> idToken = new HashMap<>();
        Instant instant = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus(100, ChronoUnit.MINUTES))
                .issuer("Customers-service")
                .claim("scope", scope)
                .build();
        String jwtAccessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        idToken.put("accessToken", jwtAccessToken);

        Customer user = customerRepository.findByEmail(email);
        user.setPassword(null);
        idToken.put("user", user);


        return new ResponseEntity<>(idToken, HttpStatus.OK);
    }
}