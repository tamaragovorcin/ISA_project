package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.Users.Mark;
import com.isaproject.isaproject.Model.Users.MarkMedication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkMedicationRepository extends JpaRepository<MarkMedication, Integer> {
}
