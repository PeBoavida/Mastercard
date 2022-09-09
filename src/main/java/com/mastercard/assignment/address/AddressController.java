package com.mastercard.assignment.address;

import com.mastercard.assignment.address.dto.AddressDTO;
import com.mastercard.assignment.address.dto.CreateAddressDTO;
import com.mastercard.assignment.address.service.AddressService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@Tag(name = "Address", description = "Endpoints to manage address information.")
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/{customerId:[0-9]+}/address/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "The address has been created",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "The address creation failed")
            })
    private String createAddress(@PathVariable @Parameter(description = "Customer id to add this address.", example = "1", required = true) Long customerId, @RequestBody @Valid CreateAddressDTO createAddressDTO) {
        Long createdAddressId = addressService.createAddress(customerId, createAddressDTO);
        return createdAddressId.toString();
    }

    @GetMapping("/{customerId:[0-9]+}/addresses")
    public List<AddressDTO> getAddress(
            @Parameter(description = "Customer id owner of the addresses.", required = true, example = "1")
            @PathVariable
                    Long customerId) {
        return addressService.getAddressByCustomerId(customerId);
    }
}
