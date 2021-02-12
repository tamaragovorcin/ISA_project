package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.Users.Dermatologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

public interface DermatologistRepository extends JpaRepository<Dermatologist, Integer> {
    Dermatologist findByEmail(String email);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from PersonUser p where p.id = :id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="10")})
    public Dermatologist findOneById(@Param("id")Integer id);
}
