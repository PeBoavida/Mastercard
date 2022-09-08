package com.mastercard.assignment.customer.model;

import com.mastercard.assignment.address.model.Address;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="Customer")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Address> addresses;
}
