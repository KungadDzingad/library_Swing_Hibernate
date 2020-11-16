package com.library.dao;

import com.library.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class BookDao extends GeneralDao<Book> {

//    @Override
//    public void save(Book book) {
//        EntityManager manager = DatabaseConnection.getManager();
//        EntityTransaction transaction = null;
//        try{
//            transaction = manager.getTransaction();
//            transaction.begin();
//
//            for (BookItem bookItem : book.getBookItems()) {
//               try{
//                   manager.persist(bookItem);
//               }catch(Exception ignored){}
//            }
//
//            manager.persist(book);
//
//            transaction.commit();
//
//        } catch (Exception e) {
//            if(transaction!=null)transaction.rollback();
//            e.printStackTrace();
//        }
//    }

    @Override
    public List<Book> getAll() {
        List<Book> books = null;

        EntityManager manager = DatabaseConnection.getManager();
        String query = "SELECT b FROM Book b";
        TypedQuery<Book> tq = manager.createQuery(query, Book.class);
        try{
            books = tq.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book refresh(Book book) {
        EntityManager entityManager = DatabaseConnection.getManager();
        Book ac = entityManager.find(Book.class,book.getIsbn());
        entityManager.refresh(ac);
        return ac;
    }

    @Override
    public List<Book> refresh(List<Book> t) {
        EntityManager entityManager = DatabaseConnection.getManager();
        List<Book> booksRefreshed = new ArrayList<>();
        for (Book b : t) {
            Book refreshed = entityManager.find(Book.class,b.getIsbn());
            entityManager.refresh(refreshed);
            booksRefreshed.add(refreshed);
        }
        return booksRefreshed;
    }
}
