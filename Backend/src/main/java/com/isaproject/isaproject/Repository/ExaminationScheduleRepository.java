package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExaminationScheduleRepository extends JpaRepository<ExaminationSchedule, Integer> {

}
