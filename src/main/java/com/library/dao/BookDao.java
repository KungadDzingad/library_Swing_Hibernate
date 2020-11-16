package com.library.dao;

import com.library.Account;
import com.library.Book;
import com.library.DatabaseConnection;
import com.library.LibraryManagementSystem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class BookDao extends GeneralDao<Book> {

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
