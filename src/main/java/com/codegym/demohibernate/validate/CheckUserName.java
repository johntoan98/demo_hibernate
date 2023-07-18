package com.codegym.demohibernate.validate;

import com.codegym.demohibernate.dao.AccountDAO;
import com.codegym.demohibernate.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CheckUserName implements Validator {
    @Autowired
    AccountDAO accountDAO;
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Account account = (Account) target;
        Account account1 = accountDAO.findByUserName(account.getUsername());

        if (account1 != null){
            errors.rejectValue("username", "", "username da ton tai");
        }
    }
}
