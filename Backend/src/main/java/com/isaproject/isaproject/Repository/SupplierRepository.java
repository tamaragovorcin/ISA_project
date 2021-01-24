package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.Users.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    Supplier findByEmail(String email);
}
