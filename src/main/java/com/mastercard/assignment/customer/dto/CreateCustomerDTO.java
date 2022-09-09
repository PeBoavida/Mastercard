package com.mastercard.assignment.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateCustomerDTO {

    @NotNull
    @Schema(description = "First name of this new customer.", example = "John", required = true)
    private String firstName;

    @NotNull
    @Schema(description = "Last name of this new customer.", example = "Doe", required = true)
    private String lastName;
}
