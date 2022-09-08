package com.mastercard.assignment.address;

import com.mastercard.assignment.address.dto.CreateAddressDTO;
import com.mastercard.assignment.address.service.AddressService;
import com.mastercard.assignment.address.dto.AddressDTO;
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
@RequestMapping("address")
@RequiredArgsConstructor
@Tag(name="Address", description = "Endpoints to manage address information.")
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The address has been created", content = @Content),
            @ApiResponse(responseCode = "500", description = "The address creation failed")})
    private String createAddress( @Parameter(name = "Create Customer", required = true)
                                   @RequestBody
                                   @Valid
                                               CreateAddressDTO createAddressDTO ){
        Long addressId = addressService.createAddress(createAddressDTO);
        return ""+addressId;
    }

    @GetMapping("/{addressId:[0-9]+}")
    private AddressDTO getAddress(@PathVariable Long addressId ){
        return addressService.getAddress(addressId);
    }
}
