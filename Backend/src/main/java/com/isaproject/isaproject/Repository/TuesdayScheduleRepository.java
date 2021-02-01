package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.HelpModel.MedicationReservation;
import com.isaproject.isaproject.Model.Schedule.TuesdaySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TuesdayScheduleRepository extends JpaRepository<TuesdaySchedule, Integer> {
}

