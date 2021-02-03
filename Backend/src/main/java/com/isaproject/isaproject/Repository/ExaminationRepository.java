package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.Examinations.Examination;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExaminationRepository extends JpaRepository<Examination, Integer> {
}
