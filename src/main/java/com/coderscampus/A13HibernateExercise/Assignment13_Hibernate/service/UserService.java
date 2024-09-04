package com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.service;

import com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.domain.Account;
import com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.domain.Address;
import com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.domain.User;
import com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.repository.AccountRepository;
import com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.repository.AddressRepository;
import com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AddressRepository addressRepo;

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    @Lazy
    private AddressService addressService;

    public Set<User> findAll() { //<- Method declaration
        List<User> userList = new ArrayList<>(userRepo.findAll()); //<- Fetching all  users
        Set<User> distinctUsers = new HashSet<>(userList); //<- Removing duplicates
        return distinctUsers; //<- Returning results
    }

    public User findById(Long userId) {
        Optional<User> userOpt = userRepo.findById(userId);
        return userOpt.orElse(new User());
    }

    public User saveUser(User user) {
        if (user.getUserId() == null) {
            userRepo.save(user);
        }
        User existingUser = findById(user.getUserId());

        if (existingUser != null) {
            if (user.getUsername() != null) {
                existingUser.setUsername(user.getUsername());
            }
            if (user.getName() != null) {
                existingUser.setName(user.getName());
            }
            if (user.getPassword() != null) {
                existingUser.setPassword((user.getPassword()));
            }
            return userRepo.save(existingUser);
        } else {
            return userRepo.save(existingUser);
        }
    }

    public User saveUser(User user, Address address) throws Exception {
        User existingUser = userRepo.findById(user.getUserId())
                .orElseThrow(() -> new Exception("user not found with id"));

        existingUser.setName(user.getName());
        existingUser.setUsername(user.getUsername());
        if (!user.getPassword().isEmpty()) {
            existingUser.setPassword(user.getPassword());
        }

        Long userId = existingUser.getUserId();
        Address existingAddress = addressRepo.findAddressByUserId(userId);

        if (existingAddress != null) {
            existingAddress.setAddressLine1(address.getAddressLine1());
            existingAddress.setAddressLine2(address.getAddressLine2());
            existingAddress.setCity(address.getCity());
            existingAddress.setRegion(address.getRegion());
            existingAddress.setZipCode(address.getZipCode());
            existingAddress.setCountry(address.getCountry());
            addressRepo.save(existingAddress); // Update
        } else {
            address.setUser(existingUser);
            addressRepo.save(address);
        }

        userRepo.save(existingUser);
        return existingUser;
    }

    public void delete(Long userId) {
        userRepo.deleteById(userId);
    }

    public Account findAccountById(Long accountId) {
        Optional<Account> accountOpt = accountRepo.findById(accountId);
        return accountOpt.orElse(null);
    }

    public Account saveAccount(Account account) {
        return accountRepo.save(account);
    }

    public Account createAccount(Long userId) {
        User user = findById(userId);

        if (user != null) {
            String accountName = "Account #" + (user.getAccounts().size() + 1);
            Optional<Account> existingAccount = accountRepo.findByAccountName(accountName);

            if (existingAccount.isEmpty()) {
                Account newAccount = new Account();
                newAccount.setAccountName(accountName);

                user.getAccounts().add(newAccount);
                newAccount.getUsers().add(user);

                userRepo.save(user);
                return accountRepo.save(newAccount); //Create
            } else {
                return existingAccount.get();
            }
        }
        return null;
    }
}
