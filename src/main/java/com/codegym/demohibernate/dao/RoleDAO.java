package com.codegym.demohibernate.dao;

import com.codegym.demohibernate.model.Account;
import com.codegym.demohibernate.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Component
public class RoleDAO {
    @Autowired
    EntityManager entityManager;

    public Role findById(int id){
        String queryStr = "SELECT a FROM Role a where a.id = :id";
        TypedQuery<Role> query = entityManager.createQuery(queryStr, Role.class).setParameter("id", id);
        return  query.getSingleResult();
    }
}
