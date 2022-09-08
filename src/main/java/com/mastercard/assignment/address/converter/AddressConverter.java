package com.mastercard.assignment.address.converter;

import com.mastercard.assignment.address.model.Address;
import com.mastercard.assignment.address.dto.AddressDTO;
import com.mastercard.assignment.customer.model.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter implements Converter<Address, AddressDTO> {

    @Override
    public AddressDTO convert(Address address) {
        Customer customer = address.getCustomer();
        return AddressDTO.builder()
                         .customerId(customer.getId())
                         .addressType(address.getAddressType())
                         .streetAddress(address.getStreetAddress())
                         .city(address.getCity())
                         .state(address.getState())
                         .zipCode(address.getZipCode())
                         .build();
    }
}
