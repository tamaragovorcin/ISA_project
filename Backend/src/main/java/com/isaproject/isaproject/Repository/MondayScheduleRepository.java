package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.Schedule.MondaySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MondayScheduleRepository extends JpaRepository<MondaySchedule, Integer> {
        }
