package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.Users.Authority;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    Authority findByName(String name);
}