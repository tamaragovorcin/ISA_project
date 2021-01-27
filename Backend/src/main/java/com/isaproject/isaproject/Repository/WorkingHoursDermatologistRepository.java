package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.Schedule.WorkingHoursDermatologist;
import com.isaproject.isaproject.Model.Users.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkingHoursDermatologistRepository extends JpaRepository<WorkingHoursDermatologist, Integer> {
}
