package com.library.dao;

import com.library.BookItem;
import com.library.DatabaseConnection;
import com.library.LibraryManagementSystem;
import com.mysql.cj.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class BookItemDao extends GeneralDao<BookItem> {



    @Override
    public List<BookItem> getAll() {
        List<BookItem> bookItems = null;

        EntityManager manager = DatabaseConnection.getManager();
        String query = "SELECT b FROM BookItem b";
        TypedQuery<BookItem> tq = manager.createQuery(query, BookItem.class);
        try{
            bookItems = tq.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookItems;
    }

    @Override
    public void delete(BookItem t) {
        EntityManager entityManager = DatabaseConnection.getManager();
        EntityTransaction transaction = null;
        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            BookItem bookItem = entityManager.find(BookItem.class, t.getSignature());
            entityManager.remove(bookItem);


            transaction.commit();

        } catch (Exception e) {
            if(transaction!=null)
                transaction.rollback();
            e.printStackTrace();
        }

    }

}
