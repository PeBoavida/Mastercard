package com.mastercard.assignment.customer.converter;

import com.mastercard.assignment.address.converter.AddressConverter;
import com.mastercard.assignment.address.model.Address;
import com.mastercard.assignment.address.dto.AddressDTO;
import com.mastercard.assignment.customer.dto.CustomerDTO;
import com.mastercard.assignment.customer.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomerConverter implements Converter<Customer, CustomerDTO> {

    private final AddressConverter addressConverter;

    @Override
    public CustomerDTO convert(Customer customer) {
        return CustomerDTO.builder()
                          .id(customer.getId())
                          .firstName(customer.getFirstName())
                          .lastName(customer.getLastName())
                          .addresses(convertAddresses(customer.getAddresses()))
                          .build();
    }

    private List<AddressDTO> convertAddresses(List<Address> addresses) {
        return addresses.stream().map(addressConverter::convert).collect(Collectors.toList());
    }

}
