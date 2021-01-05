package com.acyuta.springsecurity2.domain.controller.customer;

import com.acyuta.springsecurity2.domain.model.Customer;
import com.acyuta.springsecurity2.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;



    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        return customerService.getAllCustomers();
    }

    @PostMapping("/customers/add")
    public Customer customer(@RequestBody @Valid CustomerDto customerDto){
        return customerService.addCustomer(customerDto);

    }

}
