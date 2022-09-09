package com.mastercard.assignment.address.dto;

import com.mastercard.assignment.address.enums.AddressTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    @Schema(description = "Address Id.", example = "123", required = true)
    private Long addressId;

    @Schema(
            description = "Type of the address (Enum). It can either be Billing or Shipping.",
            example = "BILLING",
            required = true)
    private AddressTypeEnum addressType;

    @Schema(
            description = "Customer id returned in the create customer endpoint.",
            example = "123",
            required = true)
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
