package com.acyuta.springsecurity2.domain.service;

import com.acyuta.springsecurity2.domain.controller.customer.CustomerDto;
import com.acyuta.springsecurity2.domain.model.Authority;
import com.acyuta.springsecurity2.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;

    private Set<Authority> getAuthorities(Set<String> auth) {
        var authorities = new HashSet<Authority>();

        auth
                .forEach(a -> authorities.add(authorityRepository.findByName(a.toUpperCase()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "no such authority"))));
        return authorities;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer addCustomer(CustomerDto customerDto) {


        var newCustomer = new Customer()
                .setEmail(customerDto.getEmail())
                .setPwd(passwordEncoder.encode(customerDto.getPwd()))
                .setAuthorities(getAuthorities(customerDto.getAuthorities()));
        return customerRepository.save(newCustomer);
    }
}
