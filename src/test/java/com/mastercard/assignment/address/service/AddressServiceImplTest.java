package com.mastercard.assignment.address.service;

import com.mastercard.assignment.address.converter.AddressConverter;
import com.mastercard.assignment.address.dto.AddressDTO;
import com.mastercard.assignment.address.dto.CreateAddressDTO;
import com.mastercard.assignment.address.model.Address;
import com.mastercard.assignment.address.repository.AddressRepository;
import com.mastercard.assignment.customer.model.Customer;
import com.mastercard.assignment.customer.repository.CustomerRepository;
import com.mastercard.assignment.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class AddressServiceImplTest {

    @MockBean
    private AddressRepository addressRepository;
    @MockBean
    private CustomerRepository customerRepository;
    @MockBean
    private AddressConverter addressConverter;

    @Autowired
    private AddressServiceImpl addressService;

    @Test
    void createAddressShouldReturnNewAddressId() {
        // declare mocks
        CreateAddressDTO mockCreateAddressDTO = mock(CreateAddressDTO.class);
        Address mockAddress = mock(Address.class);
        Customer mockCustomer = mock(Customer.class);

        // stubbing
        when(mockAddress.getId()).thenReturn(321l);
        when(addressRepository.save(any())).thenReturn(mockAddress);
        when(customerRepository.findById(123l))
                .thenReturn(Optional.of(mockCustomer));

        // actual method to be tested call
        Long returnedId = addressService.createAddress(123l, mockCreateAddressDTO);

        // assert return
        assertThat(returnedId).isEqualTo(321l);
    }

    @Test
    void createAddressWithInvalidCustomerIdShouldThrowResourceNotFoundException() {
        // declare mocks
        CreateAddressDTO mockCreateAddressDTO = mock(CreateAddressDTO.class);
        Address mockAddress = mock(Address.class);

        // stubbing
        when(mockAddress.getId()).thenReturn(321l);
        when(customerRepository.findById(123l))
                .thenReturn(Optional.ofNullable(null));

        // assert return
        assertThrows(ResourceNotFoundException.class, () -> addressService.createAddress(123l, mockCreateAddressDTO));
    }

    @Test
    void getAddressShouldReturnAddress() {
        // declare mocks
        Customer mockCustomer = mock(Customer.class);
        AddressDTO mockAddressDTO = mock(AddressDTO.class);

        // stubbing
        when(customerRepository.findById(123l)).thenReturn(Optional.of(mockCustomer));
        when(mockCustomer.getAddresses()).thenReturn(List.of(mock(Address.class)));
        when(addressConverter.convert(any())).thenReturn(mockAddressDTO);

        // actual method to be tested call
        List<AddressDTO> returnedAddressDTO = addressService.getAddressByCustomerId(123l);

        // assert return
        assertThat(returnedAddressDTO).containsExactly(mockAddressDTO);
    }

    @Test
    void getAddressShouldThrowResourceNotFoundException() {
        // declare mocks
        AddressDTO mockAddressDTO = mock(AddressDTO.class);

        // stubbing
        when(addressRepository.findById(321l)).thenReturn(Optional.ofNullable(null));
        when(addressConverter.convert(any())).thenReturn(mockAddressDTO);

        // assert return
        assertThrows(ResourceNotFoundException.class, () -> addressService.getAddressByCustomerId(321l));
    }

}
