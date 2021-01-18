package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.Users.ConfirmationToken;
import com.isaproject.isaproject.Model.Users.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
