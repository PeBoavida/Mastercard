package com.mastercard.assignment.customer.service;

import com.mastercard.assignment.customer.converter.CustomerConverter;
import com.mastercard.assignment.customer.dto.CreateCustomerDTO;
import com.mastercard.assignment.customer.dto.CustomerDTO;
import com.mastercard.assignment.customer.model.Customer;
import com.mastercard.assignment.customer.repository.CustomerRepository;
import com.mastercard.assignment.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerServiceImplTest {

    @MockBean
    private CustomerRepository customerRepository;
    @MockBean
    private CustomerConverter customerConverter;

    @Autowired
    private CustomerServiceImpl customerService;

    @Test
    void createCustomerShouldReturnNewCustomerId() {
        // declare mocks
        CreateCustomerDTO mockCreateCustomerDTO = mock(CreateCustomerDTO.class);
        Customer mockCustomer = mock(Customer.class);

        // stubbing
        when(mockCustomer.getId()).thenReturn(123l);
        when(customerRepository.save(any())).thenReturn(mockCustomer);
        when(customerRepository.findById(mockCustomer.getId())).thenReturn(Optional.of(mockCustomer));

        // actual method to be tested call
        Long returnedId = customerService.createCustomer(mockCreateCustomerDTO);

        // assert return
        assertThat(returnedId).isEqualTo(123l);
    }

    @Test
    void getCustomerShouldReturnAddress() {
        // declare mocks
        CustomerDTO mockCustomerDTO = mock(CustomerDTO.class);

        // stubbing
        when(customerRepository.findById(123l)).thenReturn(Optional.of(mock(Customer.class)));
        when(customerConverter.convert(any())).thenReturn(mockCustomerDTO);

        // actual method to be tested call
        CustomerDTO returnedCustomerDTO = customerService.getCustomer(123l);

        // assert return
        assertThat(returnedCustomerDTO).isEqualTo(mockCustomerDTO);
    }

    @Test
    void getCustomerShouldThrowResourceNotFoundException() {
        // declare mocks
        CustomerDTO mockCustomerDTO = mock(CustomerDTO.class);

        // stubbing
        when(customerRepository.findById(123l)).thenReturn(Optional.ofNullable(null));
        when(customerConverter.convert(any())).thenReturn(mockCustomerDTO);

        // assert return
        assertThrows(ResourceNotFoundException.class, () -> customerService.getCustomer(123l));
    }

}
