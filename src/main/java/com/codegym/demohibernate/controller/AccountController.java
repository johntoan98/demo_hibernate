package com.codegym.demohibernate.controller;

import com.codegym.demohibernate.dao.AccountDAO;
import com.codegym.demohibernate.dao.RoleDAO;
import com.codegym.demohibernate.model.Account;
import com.codegym.demohibernate.model.Role;
import com.codegym.demohibernate.service.AccountService;
import com.codegym.demohibernate.validate.CheckUserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class AccountController {
    @Autowired
    AccountService accountService;

    @ModelAttribute(name = "accounts")
    public List<Account> accounts(){
        return accountService.getAll();
    }

    @GetMapping("accounts")
    public String getAll(Model model) {
        return "accounts";
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("account", new Account());
        return modelAndView;
    }

    @Autowired
    CheckUserName checkUserName;

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("account") Account account1,BindingResult bindingResult, int idRole, @RequestParam MultipartFile imgFile) {
        checkUserName.validate(account1,bindingResult);
        if (bindingResult.hasErrors()) {
            return "create";
        } else {
            accountService.save(account1, idRole, imgFile);
            return "redirect:/accounts";
        }
    }
}
