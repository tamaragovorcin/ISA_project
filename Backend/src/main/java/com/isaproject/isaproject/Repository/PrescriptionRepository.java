package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.Examinations.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {
}
