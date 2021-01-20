package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.Users.Dermatologist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DermatologistRepository extends JpaRepository<Dermatologist, Integer> {
    Dermatologist findByEmail(String email);
}
