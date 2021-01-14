package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.Users.PersonUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonUserRepository extends JpaRepository<PersonUser, Integer> {
    PersonUser findByEmail( String email );
}
