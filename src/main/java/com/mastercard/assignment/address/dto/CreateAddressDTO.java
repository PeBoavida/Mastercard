package com.mastercard.assignment.address.dto;

import com.mastercard.assignment.address.enums.AddressTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "CreateAddressDTO")
@Getter
@Setter
public class CreateAddressDTO {

    @Schema(description = "Type of the address (Enum). It can either be Billing or Shipping.", example = "BILLING", required = true)
    private AddressTypeEnum addressType;

    @Schema(description = "Customer id returned in the create customer endpoint.", example = "1", required = true)
    private Long customerId;

    @Schema(description = "Full location address.", example = "1426 Webster Street", required = true)
    private String streetAddress;

    @Schema(description = "City of this address.", example = "Woodbridge", required = true)
    private String city;

    @Schema(description = "State of this address.", example = "New Jersey", required = true)
    private String state;

    @Schema(description = "Zip code of this address.", example = "07095", required = true)
    private String zipCode;
}
