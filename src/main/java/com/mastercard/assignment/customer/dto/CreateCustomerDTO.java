package com.mastercard.assignment.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateCustomerDTO {

    @Schema(description = "First name of this new customer.", example = "John", required = true)
    private String firstName;
    @Schema(description = "Last name of this new customer.", example = "Doe", required = true)
    private String lastName;

}
