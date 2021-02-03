package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Users.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkRepository  extends JpaRepository<Mark, Integer> {
}
