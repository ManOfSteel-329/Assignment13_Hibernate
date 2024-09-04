package com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.web;

import com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.domain.Account;
import com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.domain.User;
import com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/{userId}/accounts/create")
    public String postCreateAccount(ModelMap model, @PathVariable Long userId) {
        Account account = userService.createAccount(userId);
        long accountId = account.getAccountId() - 1;
        return  "redirect:/users/" + userId + "/accounts/" + accountId;
    }

    @GetMapping("/users/{userId}/accounts/{accountId}")
    public String getOneAccount(ModelMap model, @PathVariable Long userId, @PathVariable Long accountId) {
        User userById = userService.findById(userId);
        model.put("user", userById);
        Account accountById = userService.findAccountById(accountId);
        model.put("account", accountById);

        return "account-view";
    }

    @PostMapping("/users/{userId}/accounts/{accountId}")
    public String updateOneAccount(@PathVariable Long userId, @PathVariable Long accountId, @ModelAttribute Account account) {
        Account existingAccount = userService.findAccountById(accountId);

        if (existingAccount != null) {
            // account.getAcccountName() is the latest name coming from the frontend (it's
            existingAccount.setAccountName(account.getAccountName());
            userService.saveAccount(existingAccount);
            return "redirect:/users/" +userId + "/accounts/" + existingAccount.getAccountId();
        }

        return "redirect:/error";
    }
}
