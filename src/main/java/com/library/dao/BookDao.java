package com.library.dao;

import com.library.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class BookDao extends GeneralDao<Book> {

    public BookDao(EntityManagerFactory factory) {
        super(factory);
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = null;



        EntityManager manager = factory.createEntityManager();
        String query = "SELECT b FROM Book b";
        TypedQuery<Book> tq = manager.createQuery(query, Book.class);
        try{
            books = tq.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }
}
