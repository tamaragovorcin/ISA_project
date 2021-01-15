package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.PersonUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findByEmail(String email );
}
