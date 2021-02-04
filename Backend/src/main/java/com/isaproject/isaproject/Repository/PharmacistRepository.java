
package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Integer> {
    Pharmacist findByEmail(String email);
    List<Pharmacist> findByPharmacy(Pharmacy pharmacy);
    @Query(value = "SELECT * from PersonUser t where t.markPharmacist BETWEEN ?1 AND ?2", nativeQuery=true)
    List<Pharmacist> findAllByMarkBetweenMinAndMax(int markMin, int markMax);
}
