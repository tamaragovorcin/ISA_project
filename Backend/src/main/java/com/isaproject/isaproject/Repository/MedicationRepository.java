package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.PharmacyAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, Integer> {
    Medication findByName(String name);

    List<Medication> findByForm(String form);

    List<Medication> findByType(String type);

    @Query(value = "SELECT * from Medication t where t.mark BETWEEN ?1 AND ?2", nativeQuery=true)
    List<Medication> findAllByMarkBetweenMinAndMax(int markMin, int markMax);
}
