package com.library.dao;

import com.library.Book;
import com.library.DatabaseConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class BookDao implements  DAO<Book>{
    private EntityManagerFactory factory;

    public BookDao(EntityManagerFactory factory){
        this.factory = factory;
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

    @Override
    public void save(Book book) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = null;
        try{
            transaction = manager.getTransaction();
            transaction.begin();

            manager.persist(book);

            transaction.commit();

        } catch (Exception e) {
            if(transaction!=null)transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Book book) {

    }
}
