package com.codegym.demohibernate.controller;

import com.codegym.demohibernate.dao.AccountDAO;
import com.codegym.demohibernate.dao.RoleDAO;
import com.codegym.demohibernate.model.Account;
import com.codegym.demohibernate.model.Role;
import com.codegym.demohibernate.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("accounts")
    public String getAll(Model model){
        List<Account> accounts = accountService.getAll();
        model.addAttribute("accounts", accounts);
        return "accounts";
    }
    @GetMapping("/create")
    public ModelAndView showCreate(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("account", new Account());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(Account account, int idRole, @RequestParam MultipartFile imgFile){
        accountService.save(account,idRole,imgFile);
        return "redirect:/accounts";
    }


}
