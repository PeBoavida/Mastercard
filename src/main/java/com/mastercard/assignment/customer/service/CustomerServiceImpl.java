package com.mastercard.assignment.customer.service;

import com.mastercard.assignment.customer.converter.CustomerConverter;
import com.mastercard.assignment.customer.dto.CreateCustomerDTO;
import com.mastercard.assignment.customer.dto.CustomerDTO;
import com.mastercard.assignment.customer.model.Customer;
import com.mastercard.assignment.customer.repository.CustomerRepository;
import com.mastercard.assignment.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    public Long createCustomer(CreateCustomerDTO createCustomerDTO) {
        Customer customer =
                Customer.builder()
                        .firstName(createCustomerDTO.getFirstName())
                        .lastName(createCustomerDTO.getLastName())
                        .build();
        customer = customerRepository.save(customer);
        return customer.getId();
    }

    public CustomerDTO getCustomer(Long customerId) {
        Customer customer =
                customerRepository
                        .findById(customerId)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Could not find customer for id " + customerId + "."));
        return customerConverter.convert(customer);
    }
}
