package com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.repository;

import com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findAddressByUserId(Long userId);
}
