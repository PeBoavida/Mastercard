package com.mastercard.assignment.address.model;

import com.mastercard.assignment.customer.model.Customer;
import com.mastercard.assignment.address.enums.AddressTypeEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Address")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId", referencedColumnName="id")
    private Customer customer;

    private AddressTypeEnum addressType;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
}
