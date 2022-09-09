package com.mastercard.assignment.address.service;

import com.mastercard.assignment.address.dto.AddressDTO;
import com.mastercard.assignment.address.dto.CreateAddressDTO;

import java.util.List;

public interface AddressService {
    Long createAddress(Long customerId, CreateAddressDTO createAddressDTO);

    List<AddressDTO> getAddressByCustomerId(Long addressId);
}
