package com.example.customersservice.services;
import com.example.customersservice.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailService implements UserDetailsService {
    private CustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer user = customerService.loadUserByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException("User with email " + email + " no found");
        GrantedAuthority role = new SimpleGrantedAuthority(user.getRole().toString());
        UserDetails userDetails = User.withUsername(email).password(user.getPassword()).authorities(role)
                .build();
        System.out.println(userDetails);
        return userDetails;
    }

}