package com.deesha.medicine_distribution.repository;

import com.deesha.medicine_distribution.model.Companies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompaniesRepository extends JpaRepository<Companies,Long> {
// Fetch company with its addresses and enterprise type in one query
    @Query("""
           SELECT DISTINCT c FROM Companies c
           LEFT JOIN FETCH c.addresses
           LEFT JOIN FETCH c.enterpriseType
           WHERE c.companyId = :id
           """)
    Optional<Companies> findWithAddresses(@Param("id") Integer id);
}