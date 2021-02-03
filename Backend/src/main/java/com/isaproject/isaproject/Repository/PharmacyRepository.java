package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer> {
    Pharmacy findByPharmacyName(String pharmacyName);

    @Query(value = "SELECT pharmacyName from pharmacy_table", nativeQuery=true)
    List<Pharmacy> getAllPharmacyNames();
}
