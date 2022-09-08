package com.mastercard.assignment.address.service;

import com.mastercard.assignment.address.dto.CreateAddressDTO;
import com.mastercard.assignment.address.model.Address;
import com.mastercard.assignment.address.repository.AddressRepository;
import com.mastercard.assignment.address.converter.AddressConverter;
import com.mastercard.assignment.address.dto.AddressDTO;
import com.mastercard.assignment.customer.model.Customer;
import com.mastercard.assignment.customer.repository.CustomerRepository;
import com.mastercard.assignment.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    private final AddressConverter addressConverter;

    @Override
    public Long createAddress(CreateAddressDTO createAddressDTO) {
        Customer customer = customerRepository.findById(createAddressDTO.getCustomerId()).orElseThrow(()->new RuntimeException("Not found customer for id"+createAddressDTO.getCustomerId()));
        Address address = Address.builder()
                                 .addressType(createAddressDTO.getAddressType())
                                 .customer(customer)
                                 .streetAddress(createAddressDTO.getStreetAddress())
                                 .city(createAddressDTO.getCity())
                                 .state(createAddressDTO.getState())
                                 .zipCode(createAddressDTO.getZipCode())
                                 .build();
        addressRepository.save(address);
        return address.getId();
    }

    @Override
    public AddressDTO getAddress(Long addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(()->new ResourceNotFoundException("Couldn't find address with id "+addressId+"."));
        return addressConverter.convert(address);
    }

}
