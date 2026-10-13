package com.deesha.medicine_distribution.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// CompanyAddress.java
@Entity @Getter @Setter
@Table(name = "company_address",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_company_address_type",
                columnNames = {"company_id", "address_type"}))
public class CompanyAddress {

    public enum AddressType { REGISTERED, FACTORY, CORRESPONDENCE }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Companies company;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type", nullable = false, length = 20)
    private AddressType addressType;

    @Column(name = "address_line1", nullable = false)
    private String addressLine1;

    @Column(name = "address_line2")
    private String addressLine2;

    // district, state, country are derived: city -> district -> state -> country
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cities_id", nullable = false)
    private Cities city;

    @Column(name = "pincode", nullable = false, length = 6)
    private String pincode;
}