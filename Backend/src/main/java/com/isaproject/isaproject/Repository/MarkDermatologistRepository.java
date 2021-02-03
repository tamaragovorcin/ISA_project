package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.Users.MarkDermatologist;
import com.isaproject.isaproject.Model.Users.MarkMedication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkDermatologistRepository extends JpaRepository<MarkDermatologist, Integer> {
}
