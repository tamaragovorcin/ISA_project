package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.HelpModel.MedicationReservation;
import com.isaproject.isaproject.Model.Medicine.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationReservationRepository extends JpaRepository<MedicationReservation, Integer> {
}
