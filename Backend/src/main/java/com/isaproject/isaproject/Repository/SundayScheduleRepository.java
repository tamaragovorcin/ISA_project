package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.HelpModel.MedicationReservation;
import com.isaproject.isaproject.Model.Schedule.SundaySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SundayScheduleRepository extends JpaRepository<SundaySchedule, Integer> {
}
