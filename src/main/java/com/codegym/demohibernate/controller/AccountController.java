package com.codegym.demohibernate.controller;

import com.codegym.demohibernate.dao.AccountDAO;
import com.codegym.demohibernate.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AccountController {
    @Autowired
    AccountDAO accountDAO;

    @GetMapping("accounts")
    public String getAll(Model model){
        List<Account> accounts = accountDAO.getAll();
        model.addAttribute("accounts", accountDAO.getAll());
        return "accounts";
    }
}
