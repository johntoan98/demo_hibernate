package com.codegym.demohibernate.dao;

import com.codegym.demohibernate.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class AccountDAO {
    @Autowired
    EntityManager entityManager;

    public List<Account> getAll(){
        String queryStr = "SELECT a FROM Account a";
        TypedQuery<Account> query = entityManager.createQuery(queryStr, Account.class);
        return query.getResultList();
    }

    public Account findById(int id){
        String queryStr = "SELECT a FROM Account a where a.id = :id";
        TypedQuery<Account> query = entityManager.createQuery(queryStr, Account.class).setParameter("id", id);
        return  query.getSingleResult();
    }


    public void save(Account account){
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        entityManager.persist(account);
        txn.commit();
    }

    public void edit(Account account){
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        entityManager.merge(account);
        txn.commit();
    }

    public void delete(Account account){
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        entityManager.remove(account);
        txn.commit();
    }



}
