package com.mastercard.assignment.customer;

import com.mastercard.assignment.address.dto.AddressDTO;
import com.mastercard.assignment.customer.dto.CreateCustomerDTO;
import com.mastercard.assignment.customer.dto.CustomerDTO;
import com.mastercard.assignment.customer.service.CustomerService;
import com.mastercard.assignment.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private CustomerService customerService;

    @Test
    void createCustomerEndpointShouldReturnNewCustomerId() {
        CreateCustomerDTO input = CreateCustomerDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .build();
        when(customerService.createCustomer(any())).thenReturn(123l);
        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/customer/create", input,
                String.class)).isEqualTo("123");
    }

    @Test
    void getCustomerEndpointShouldReturnCustomer() {
        CustomerDTO mockCustomerDTO = CustomerDTO.builder()
                .id(123l)
                .build();
        Mockito.when(customerService.getCustomer(mockCustomerDTO.getId())).thenReturn(mockCustomerDTO);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/customer/123",
                CustomerDTO.class)).isEqualTo(mockCustomerDTO);
    }

    @Test
    void getNoneExistingCustomerShouldThrowResourceNotFoundException() {
        Mockito.when(customerService.getCustomer(123l)).thenThrow(new ResourceNotFoundException("Not found"));
        HttpStatusCode returnedHttpStatusCode = this.restTemplate.getForEntity("http://localhost:" + port + "/customer/123", AddressDTO.class).getStatusCode();
        assertThat(returnedHttpStatusCode).isEqualTo(HttpStatus.NOT_FOUND);
    }

}