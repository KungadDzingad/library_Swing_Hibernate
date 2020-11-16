package com.library.dao;

import com.library.Account;
import com.library.DatabaseConnection;
import com.library.LibraryManagementSystem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public abstract class GeneralDao<T> implements DAO<T>{


    @Override
    public void save(T t) {
        EntityManager manager = DatabaseConnection.getManager();
        EntityTransaction transaction = null;
        try{
            transaction = manager.getTransaction();
            transaction.begin();

            manager.persist(t);

            transaction.commit();

        } catch (Exception e) {
            if(transaction!=null)transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(T t) {
        EntityManager entityManager = DatabaseConnection.getManager();
        EntityTransaction transaction = null;
        try{
            transaction = entityManager.getTransaction();
            transaction.begin();


            entityManager.remove(t);

            transaction.commit();

        } catch (Exception e) {
            if(transaction!=null)
                transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public T refresh(T t) {
        return null;
    }

    @Override
    public List<T> refresh(List<T> t) {
        return null;
    }
}
