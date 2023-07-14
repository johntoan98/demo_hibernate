package com.codegym.demohibernate.dao;

import com.codegym.demohibernate.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class AccountDAO {
    @PersistenceContext
    EntityManager entityManager;

    public List<Account> getAll(){
        String queryStr = "SELECT a FROM Account a";
        TypedQuery<Account> query = entityManager.createQuery(queryStr, Account.class);
        return query.getResultList();
    }

    public Account findById(int id){
        return entityManager.find(Account.class, id);
    }

    public void save(Account account){
        entityManager.persist(account);

    }

    public void edit(Account account){
        entityManager.merge(account);
    }

    public void delete(Account account){
        entityManager.remove(account.getId());
    }



}
