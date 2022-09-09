package com.mastercard.assignment.customer.dto;

import com.mastercard.assignment.address.dto.AddressDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class CustomerDTO {

    @NotNull
    @Schema(
            description =
                    "Customer id. This field should be used as a identifier for the customer object.",
            example = "123",
            required = true)
    private Long id;

    @NotNull
    @Schema(description = "First name of this customer.", example = "John", required = true)
    private String firstName;

    @NotNull
    @Schema(description = "Last name of this customer.", example = "Doe", required = true)
    private String lastName;

    @NotNull
    @Schema(description = "Addresses of this customer.", required = true)
    private List<AddressDTO> addresses;
}
