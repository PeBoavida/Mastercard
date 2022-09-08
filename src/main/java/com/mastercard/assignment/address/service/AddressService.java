package com.mastercard.assignment.address.service;

import com.mastercard.assignment.address.dto.CreateAddressDTO;
import com.mastercard.assignment.address.dto.AddressDTO;

public interface AddressService {
    Long createAddress(CreateAddressDTO createAddressDTO);

    AddressDTO getAddress(Long addressId);
}
