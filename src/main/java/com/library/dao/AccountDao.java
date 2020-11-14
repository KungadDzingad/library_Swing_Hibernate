package com.library.dao;

import com.library.Account;

import javax.persistence.*;
import java.util.List;

public class AccountDao extends GeneralDao<Account> {

    public AccountDao(EntityManagerFactory factory) {
        super(factory);
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts = null;

        EntityManager manager = factory.createEntityManager();
        String query = "SELECT a FROM Account a";
        TypedQuery<Account> tq = manager.createQuery(query, Account.class);
        try{
            accounts = tq.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }


}
