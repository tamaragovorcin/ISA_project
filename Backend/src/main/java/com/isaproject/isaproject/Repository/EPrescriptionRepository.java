package com.isaproject.isaproject.Repository;
import com.isaproject.isaproject.Model.Examinations.EPrescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EPrescriptionRepository extends JpaRepository<EPrescription, Integer> {
    EPrescription findByCode(String code);
}

