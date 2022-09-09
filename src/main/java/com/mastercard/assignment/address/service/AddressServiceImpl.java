package com.mastercard.assignment.address.service;

import com.mastercard.assignment.address.converter.AddressConverter;
import com.mastercard.assignment.address.dto.AddressDTO;
import com.mastercard.assignment.address.dto.CreateAddressDTO;
import com.mastercard.assignment.address.model.Address;
import com.mastercard.assignment.address.repository.AddressRepository;
import com.mastercard.assignment.customer.model.Customer;
import com.mastercard.assignment.customer.repository.CustomerRepository;
import com.mastercard.assignment.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    private final AddressConverter addressConverter;

    @Override
    public Long createAddress(Long customerId, CreateAddressDTO createAddressDTO) {
        Customer customer =
                customerRepository
                        .findById(customerId)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Not found customer for id " + customerId + "."));
        Address address =
                Address.builder()
                        .addressType(createAddressDTO.getAddressType())
                        .customer(customer)
                        .streetAddress(createAddressDTO.getStreetAddress())
                        .city(createAddressDTO.getCity())
                        .state(createAddressDTO.getState())
                        .zipCode(createAddressDTO.getZipCode())
                        .build();
        address = addressRepository.save(address);
        return address.getId();
    }

    @Override
    public List<AddressDTO> getAddressByCustomerId(Long customerId) {
        List<Address> addresses =
                customerRepository
                        .findById(customerId)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Couldn't find customer for id " + customerId + ".")).getAddresses();
        return addresses.stream().map(addressConverter::convert).collect(Collectors.toList());
    }
}
