package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.HelpModel.MedicationReservation;
import com.isaproject.isaproject.Model.Schedule.WednesdaySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WednesdayScheduleRepository extends JpaRepository<WednesdaySchedule, Integer> {
}
