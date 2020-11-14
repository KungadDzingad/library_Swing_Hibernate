package com.library.dao;

import com.library.BookReservation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class BookReservationDao extends GeneralDao<BookReservation> {

    public BookReservationDao(EntityManagerFactory factory) {
        super(factory);
    }

    @Override
    public List<BookReservation> getAll() {
        return null;
    }

    @Override
    public void save(BookReservation bookReservation) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = null;
        try{
            transaction = manager.getTransaction();
            transaction.begin();

            manager.persist(bookReservation);

            transaction.commit();

        } catch (Exception e) {
            if(transaction!=null)transaction.rollback();
            e.printStackTrace();
        }
    }

}
