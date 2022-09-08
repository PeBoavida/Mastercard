package com.mastercard.assignment.customer.dto;

import com.mastercard.assignment.address.dto.AddressDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CustomerDTO {

    @Schema(description = "Customer id. This field should be used as a identifier for the customer object.", example = "1", required = true)
    private Long id;

    @Schema(description = "First name of this customer.", example = "John", required = true)
    private String firstName;

    @Schema(description = "Last name of this customer.", example = "Doe", required = true)
    private String lastName;

    @Schema(description = "Addresses of this customer.", required = true)
    private List<AddressDTO> addresses;

}
