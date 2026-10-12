package com.deesha.medicine_distribution.repository;

import com.deesha.medicine_distribution.model.CompanyAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyAddressRepository extends JpaRepository<CompanyAddress,Long> {
}
