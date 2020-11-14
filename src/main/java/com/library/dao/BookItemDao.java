package com.library.dao;

import com.library.BookItem;
import com.mysql.cj.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class BookItemDao extends GeneralDao<BookItem> {

    public BookItemDao(EntityManagerFactory factory) {
        super(factory);
    }

    @Override
    public List<BookItem> getAll() {
        List<BookItem> bookItems = null;

        EntityManager manager = factory.createEntityManager();
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
        EntityManager entityManager = factory.createEntityManager();
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
