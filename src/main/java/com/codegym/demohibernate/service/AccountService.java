package com.codegym.demohibernate.service;

import com.codegym.demohibernate.dao.AccountDAO;
import com.codegym.demohibernate.dao.RoleDAO;
import com.codegym.demohibernate.model.Account;
import com.codegym.demohibernate.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountDAO accountDAO;

    @Autowired
    RoleDAO roleDAO;

    public void save(Account account, int idRole,MultipartFile imgFile){
        String nameFile = imgFile.getOriginalFilename();
        try {
            imgFile.transferTo(new File("/Users/johntoan98gmail.com/Desktop/demoHibernate/src/main/webapp/img/" + nameFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        account.setAvatar("/img/"+ nameFile);
        Role role = roleDAO.findById(idRole);
        account.setRole(role);
        accountDAO.save(account);
    }

    public void edit(Account account){
        accountDAO.edit(account);
    }

    public void delete(Account account){
        accountDAO.delete(account);
    }

    public List<Account> getAll(){
        return accountDAO.getAll();
    }



}
