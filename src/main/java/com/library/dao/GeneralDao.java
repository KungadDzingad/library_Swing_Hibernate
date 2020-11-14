package com.library.dao;

import com.library.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public abstract class GeneralDao<T> implements DAO<T>{
    protected EntityManagerFactory factory;

    public GeneralDao(EntityManagerFactory factory){
        this.factory = factory;
    }

    @Override
    public void save(T t) {
        EntityManager manager = factory.createEntityManager();
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
        EntityManager entityManager = factory.createEntityManager();
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
}
