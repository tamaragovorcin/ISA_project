package com.isaproject.isaproject.psw.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.isaproject.isaproject.psw.model.Medicine;
import com.isaproject.isaproject.psw.model.Pharmacy;

@Repository
public interface MedicineRepository extends CrudRepository<Medicine, String>{

}
