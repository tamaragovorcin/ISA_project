package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.HelpModel.MedicationReservation;
import com.isaproject.isaproject.Model.Schedule.SaturdaySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaturdayScheduleRepository extends JpaRepository<SaturdaySchedule, Integer> {
}
