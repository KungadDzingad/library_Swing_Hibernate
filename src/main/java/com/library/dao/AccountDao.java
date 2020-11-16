package com.library.dao;

import com.library.Account;
import com.library.DatabaseConnection;
import com.library.LibraryManagementSystem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao extends GeneralDao<Account> {


    @Override
    public List<Account> getAll() {
        List<Account> accounts = null;

        EntityManager manager = DatabaseConnection.getManager();
        String query = "SELECT a FROM Account a";
        TypedQuery<Account> tq = manager.createQuery(query, Account.class);
        try{
            accounts = tq.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public Account refresh(Account account) {
        EntityManager entityManager = DatabaseConnection.getManager();
        Account ac = entityManager.find(Account.class,account.getMail());
        entityManager.refresh(ac);
        return ac;
    }

    @Override
    public List<Account> refresh(List<Account> t) {
        EntityManager entityManager = DatabaseConnection.getManager();
        List<Account> accountsRefreshed = new ArrayList<>();
        for (Account account : t) {
            Account refreshed = entityManager.find(Account.class,account.getMail());
            entityManager.refresh(refreshed);
            accountsRefreshed.add(refreshed);
        }
        return accountsRefreshed;
    }
}
