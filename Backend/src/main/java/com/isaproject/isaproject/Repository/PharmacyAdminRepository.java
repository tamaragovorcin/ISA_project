package com.isaproject.isaproject.Repository;
import com.isaproject.isaproject.Model.Users.PharmacyAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyAdminRepository extends JpaRepository<PharmacyAdmin, Integer> {
    PharmacyAdmin findByEmail(String email);
}
