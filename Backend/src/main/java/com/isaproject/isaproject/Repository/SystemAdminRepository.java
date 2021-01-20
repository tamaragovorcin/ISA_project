package com.isaproject.isaproject.Repository;
import com.isaproject.isaproject.Model.Users.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemAdminRepository  extends JpaRepository<SystemAdmin, Integer> {
    SystemAdmin findByEmail(String email);
}
