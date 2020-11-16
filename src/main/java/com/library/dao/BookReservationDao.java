package com.library.dao;

import com.library.BookItem;
import com.library.BookReservation;
import com.library.DatabaseConnection;
import com.library.LibraryManagementSystem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class BookReservationDao extends GeneralDao<BookReservation> {


    @Override
    public List<BookReservation> getAll() {
        return null;
    }


    @Override
    public void delete(BookReservation reservation) {
        EntityManager entityManager = DatabaseConnection.getManager();
        EntityTransaction transaction = null;
        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            BookReservation res = entityManager.find(BookReservation.class, reservation.getId());
            entityManager.remove(res);
            transaction.commit();

        } catch (Exception e) {
            if(transaction!=null)
                transaction.rollback();
            e.printStackTrace();
        }
    }
}
