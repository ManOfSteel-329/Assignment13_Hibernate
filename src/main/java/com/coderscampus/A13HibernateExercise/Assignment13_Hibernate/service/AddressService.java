package com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.service;

import com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.domain.Address;
import com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.domain.User;
import com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.repository.AddressRepository;
import com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    @Lazy
    private UserService userService;

    @Autowired
    private AddressRepository addressRepo;

    public Address findAddressByUser(User user) {
        Long userId = user.getUserId();
        Address address = addressRepo.findAddressByUserId(userId);
        if (address == null) {
            address = new Address();
        }
        return address;
    }

    public void save(User user) {
        Address address = user.getAddress();
        userService.saveUser(user);
        addressRepo.save(address);
    }
}
