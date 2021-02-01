package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.HelpModel.MedicationReservation;
import com.isaproject.isaproject.Model.Schedule.ThursdaySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThursdayScheduleRepository extends JpaRepository<ThursdaySchedule, Integer> {
}
