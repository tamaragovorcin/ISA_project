package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.HelpModel.PatientsMedicationAlergy;
import com.isaproject.isaproject.Model.Users.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlergiesRepository extends JpaRepository<PatientsMedicationAlergy, Integer> {
}
