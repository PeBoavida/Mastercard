package com.mastercard.assignment.address.dto;

import com.mastercard.assignment.address.enums.AddressTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(name = "CreateAddressDTO")
public class CreateAddressDTO {

    @NotNull
    @Schema(
            description = "Type of the address (Enum). It can either be BILLING or SHIPPING.",
            example = "BILLING",
            required = true)
    private AddressTypeEnum addressType;

    @NotNull
    @NotBlank
    @Schema(description = "Full location address.", example = "1426 Webster Street", required = true)
    private String streetAddress;

    @NotNull
    @NotBlank
    @Schema(description = "City of this address.", example = "Woodbridge", required = true)
    private String city;

    @NotNull
    @NotBlank
    @Schema(description = "State of this address.", example = "New Jersey", required = true)
    private String state;

    @NotNull
    @NotBlank
    @Schema(description = "Zip code of this address.", example = "07095", required = true)
    private String zipCode;
}
