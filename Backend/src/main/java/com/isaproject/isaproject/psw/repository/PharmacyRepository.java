package com.isaproject.isaproject.psw.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.isaproject.isaproject.psw.model.Pharmacy;

@Repository
public interface PharmacyRepository extends CrudRepository<Pharmacy, String>{

}
