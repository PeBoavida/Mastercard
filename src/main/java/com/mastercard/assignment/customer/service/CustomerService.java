package com.mastercard.assignment.customer.service;

import com.mastercard.assignment.customer.dto.CreateCustomerDTO;
import com.mastercard.assignment.customer.dto.CustomerDTO;

public interface CustomerService {

    Long createCustomer(CreateCustomerDTO createCustomerDTO);

    CustomerDTO getCustomer(Long customerId);
}
