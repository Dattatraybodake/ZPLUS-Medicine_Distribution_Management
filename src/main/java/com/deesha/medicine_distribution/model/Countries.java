package com.deesha.medicine_distribution.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

// Countries.java
@Entity @Getter @Setter
@Table(name = "countries")
public class Countries {
    @Id
    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "iso_code")
    private String isoCode;          // "IN", "US" ... use Integer if you store numeric codes
}