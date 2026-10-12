package com.deesha.medicine_distribution.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "companies")
public class Companies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Integer companyId;

    // JOIN: companies -> enterprice_type
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enterprise_type_id")
    private EnterpriseType enterpriseType;

    // JOIN: companies -> company_address (one company, many addresses)
    @ToString.Exclude
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompanyAddress> addresses = new ArrayList<>();

    // JOIN: companies -> company_bank_account (one company, many accounts)
    @ToString.Exclude
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompanyBankAccount> bankAccounts = new ArrayList<>();

    @Column(name = "company_name", length = 90)
    private String companyName;

    @Column(name = "company_tradename", length = 90)
    private String companyTradeName;

    @Column(name = "company_cin", length = 20)
    private String companyCin;

    @Column(name = "manufacturing_license_no", length = 30)
    private String manufacturingLicenseNo;

    @Column(name = "drug_license_expiry_date")
    private LocalDate drugLicenseExpiryDate;

    @Column(name = "company_start_date")
    private LocalDate companyStartDate;

    @Column(name = "company_end_date")
    private LocalDate companyEndDate;

    @Column(name = "udyam_registration_no", length = 90)
    private String udyamRegistrationNo;

    @Column(name = "gstin", length = 15)
    private String gstin;

    @Column(name = "pan_number", length = 90)
    private String panNumber;

    @Column(name = "company_owner", length = 90)
    private String companyOwner;

    @Column(name = "owner_contact", length = 15)
    private String ownerContact;

    @Column(name = "owner_alternate_contact", length = 15)
    private String ownerAlternateContact;

    @Column(name = "authorized_signatory_name", length = 100)
    private String authorizedSignatoryName;

    @Column(name = "contact_number", length = 15)
    private String contactNumber;

    @Column(name = "alternate_contact", length = 15)
    private String alternateContact;

    @Column(name = "email_id", length = 100)
    private String emailId;

    @Column(name = "website", length = 150)
    private String website;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_by", length = 100)
    private String modifiedBy;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "deleted_by", length = 90)
    private String deletedBy;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    // Helpers keep both sides of the relationship in sync
    public void addAddress(CompanyAddress address) {
        addresses.add(address);
        address.setCompany(this);
    }

    public void removeAddress(CompanyAddress address) {
        addresses.remove(address);
        address.setCompany(null);
    }

    public void addBankAccount(CompanyBankAccount account) {
        bankAccounts.add(account);
        account.setCompany(this);
    }

    public void removeBankAccount(CompanyBankAccount account) {
        bankAccounts.remove(account);
        account.setCompany(null);
    }
}