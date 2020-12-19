package com.isaproject.isaproject.psw.repository;

import com.isaproject.isaproject.psw.model.MedicineInPharmacy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicinesPharmacyRepository extends CrudRepository<MedicineInPharmacy, String> {
}
