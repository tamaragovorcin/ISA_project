package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer> {
}
