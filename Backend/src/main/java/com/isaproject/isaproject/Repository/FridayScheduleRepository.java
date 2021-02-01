package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.HelpModel.MedicationReservation;
import com.isaproject.isaproject.Model.Schedule.FridaySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FridayScheduleRepository extends JpaRepository<FridaySchedule, Integer> {
}
