package com.mastercard.assignment.customer.converter;

import com.mastercard.assignment.address.converter.AddressConverter;
import com.mastercard.assignment.address.dto.AddressDTO;
import com.mastercard.assignment.address.model.Address;
import com.mastercard.assignment.customer.dto.CustomerDTO;
import com.mastercard.assignment.customer.model.Customer;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerConverterTest {

    @Autowired
    private CustomerConverter customerConverter;

    @MockBean
    private AddressConverter mockAddressConverter;
    @Mock
    private Address mockAddress;
    @Mock
    private AddressDTO mockAddressDTO;

    @Test
    void convertCustomerTestWithAddresses() {
        Customer customer = buildCustomerWithAddresses();
        when(mockAddressConverter.convert(mockAddress)).thenReturn(mockAddressDTO);

        CustomerDTO customerDTO = customerConverter.convert(customer);
        assertCustomerWithAddresses(customerDTO, customer);
    }

    private Customer buildCustomerWithAddresses() {
        return Customer.builder().id(1l).firstName("John").lastName("Doe").addresses(List.of(mockAddress)).build();
    }

    private void assertCustomerWithAddresses(CustomerDTO customerDTO, Customer customer) {
        assertThat(customerDTO.getId()).isEqualTo(customer.getId());
        assertThat(customerDTO.getFirstName()).isEqualTo(customer.getFirstName());
        assertThat(customerDTO.getLastName()).isEqualTo(customer.getLastName());
        assertThat(customerDTO.getAddresses()).containsExactly(mockAddressDTO);
    }

    @Test
    void convertCustomerTestWithoutAddresses() {
        Customer customer = buildCustomerWithoutAddresses();
        when(mockAddressConverter.convert(mockAddress)).thenReturn(mockAddressDTO);

        CustomerDTO customerDTO = customerConverter.convert(customer);
        assertCustomerWithoutAddresses(customerDTO, customer);
    }

    private Customer buildCustomerWithoutAddresses() {
        return Customer.builder().id(1l).firstName("John").lastName("Doe").build();
    }

    private void assertCustomerWithoutAddresses(CustomerDTO customerDTO, Customer customer) {
        assertThat(customerDTO.getId()).isEqualTo(customer.getId());
        assertThat(customerDTO.getFirstName()).isEqualTo(customer.getFirstName());
        assertThat(customerDTO.getLastName()).isEqualTo(customer.getLastName());
    }
}
