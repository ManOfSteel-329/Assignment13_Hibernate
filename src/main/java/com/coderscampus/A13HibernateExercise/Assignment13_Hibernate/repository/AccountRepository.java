package com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.repository;

import com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountName(String accountName);
}
