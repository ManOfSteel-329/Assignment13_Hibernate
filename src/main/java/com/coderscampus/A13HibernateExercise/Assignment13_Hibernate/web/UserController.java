package com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.web;

import com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.domain.Address;
import com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.domain.User;
import com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.service.AddressService;
import com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/register")
    public String getCreateUser(ModelMap model){
        //User user = new User();
        model.put("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String postCreateUser(User user){
        System.out.println(user);
        userService.saveUser(user);
        return "redirect:/register";
    }


    @GetMapping("/users")
    public String getAllUsers(ModelMap model) {
        Set<User> users = userService.findAll();
        model.put("users", users);
        return "users";
    }

    @GetMapping("/users/{userId}")
    public String getOneUser (ModelMap model, @PathVariable Long userId){
        User user = userService.findById(userId);
        Address address = addressService.findAddressByUser(user);

        model.put("user", user);
        model.put("address", address);

        return "user-view";
    }

    // Updating
    @PostMapping("/users/{userId}")
    public String postOneUser(User user, Address address) throws Exception {

        userService.saveUser(user, address);
        return "redirect:/users/" + user.getUserId();
    }

    @PostMapping("/users/{userId}/delete")
    public String deleteOneUser(@PathVariable Long userId) {
        userService.delete(userId);
        return "redirect:/users";
    }
}