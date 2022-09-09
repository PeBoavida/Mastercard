package com.mastercard.assignment.address.model;

import com.mastercard.assignment.address.enums.AddressTypeEnum;
import com.mastercard.assignment.customer.model.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Address")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customer;

    private AddressTypeEnum addressType;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
}
