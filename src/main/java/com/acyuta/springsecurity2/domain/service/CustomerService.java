package com.acyuta.springsecurity2.domain.service;

import com.acyuta.springsecurity2.domain.controller.customer.CustomerDto;
import com.acyuta.springsecurity2.domain.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer addCustomer(CustomerDto customer);
}
