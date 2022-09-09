package com.mastercard.assignment.address;

import com.mastercard.assignment.address.dto.AddressDTO;
import com.mastercard.assignment.address.dto.CreateAddressDTO;
import com.mastercard.assignment.address.enums.AddressTypeEnum;
import com.mastercard.assignment.address.service.AddressService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddressControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private AddressService addressService;

    @Test
    void createAddressEndpointShouldReturnNewAddressId() {
        CreateAddressDTO input = CreateAddressDTO.builder()
                .addressType(AddressTypeEnum.BILLING)
                .streetAddress("1426 Webster Street")
                .city("Woodbridge")
                .state("New Jersey")
                .zipCode("07095")
                .build();
        Mockito.when(addressService.createAddress(any(), any(CreateAddressDTO.class))).thenReturn(321l);
        assertThat(
                this.restTemplate.postForObject(
                        "http://localhost:" + port + "/customer/123/address/create", input, String.class))
                .isEqualTo("321");
    }

    @Test
    void getAddressEndpointShouldReturnAddress() {
        AddressDTO addressDTO = new AddressDTO();
        Mockito.when(addressService.getAddressByCustomerId(any())).thenReturn(List.of(addressDTO));
        assertThat(
                this.restTemplate.getForObject(
                        "http://localhost:" + port + "/customer/123/addresses", AddressDTO[].class))
                .containsExactly(addressDTO);
    }
}
