package com.library.dao;

import com.library.Account;
import com.library.DatabaseConnection;
import com.library.LibraryManagementSystem;

import javax.persistence.*;
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


}
