package com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.repository;

import com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
