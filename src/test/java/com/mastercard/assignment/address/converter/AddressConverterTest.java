package com.mastercard.assignment.address.converter;

import com.mastercard.assignment.address.dto.AddressDTO;
import com.mastercard.assignment.address.enums.AddressTypeEnum;
import com.mastercard.assignment.address.model.Address;
import com.mastercard.assignment.customer.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AddressConverterTest {

    @Autowired
    private AddressConverter addressConverter;

    @Test
    void convertAddressTest() {
        Address address = buildAddress();
        AddressDTO addressDTO = addressConverter.convert(address);
        assertAddress(address, addressDTO);
    }

    private Address buildAddress() {
        return Address.builder()
                .id(321l)
                .customer(Customer.builder().id(123l).firstName("John").lastName("Doe").build())
                .addressType(AddressTypeEnum.SHIPPING)
                .streetAddress("1426 Webster Street")
                .city("Woodbridge")
                .state("New Jersey")
                .zipCode("07095")
                .build();
    }

    private void assertAddress(Address address, AddressDTO addressDTO) {
        assertThat(address.getId()).isEqualTo(addressDTO.getAddressId());
        assertThat(address.getAddressType()).isEqualTo(addressDTO.getAddressType());
        assertThat(address.getCustomer().getId()).isEqualTo(addressDTO.getCustomerId());
        assertThat(address.getStreetAddress()).isEqualTo(addressDTO.getStreetAddress());
        assertThat(address.getCity()).isEqualTo(addressDTO.getCity());
        assertThat(address.getState()).isEqualTo(addressDTO.getState());
        assertThat(address.getZipCode()).isEqualTo(addressDTO.getZipCode());
    }
}
