package com.mastercard.assignment.customer;

import com.mastercard.assignment.customer.dto.CreateCustomerDTO;
import com.mastercard.assignment.customer.dto.CustomerDTO;
import com.mastercard.assignment.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
@Tag(name="Customer", description = "Endpoints to manage customer information.")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The partner has been created", content = @Content),
            @ApiResponse(responseCode = "500", description = "The partner creation failed")})
    private String createCustomer( @Parameter(name = "Create Customer", required = true)
                           @RequestBody
                           @Valid
                           CreateCustomerDTO createCustomerDTO ){
        Long customerId = customerService.createCustomer(createCustomerDTO);
        return ""+customerId;
    }

    @GetMapping("/{customerId:[0-9]+}")
    private CustomerDTO getCustomer(@PathVariable Long customerId ){
        return customerService.getCustomer(customerId);
    }

}
