package com.acyuta.springsecurity2.customUserDetails;

import com.acyuta.springsecurity2.domain.service.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * DaoAuthenticationProvider leverages UserDetailsService to call
 * loadUserByUserName, when a request is made and authentication is required.
 */
@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var customer = customerRepository.findByEmail(username);
        if(customer.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Customer not present");
        return new CustomerUserDetails(customer.get());
    }
}
