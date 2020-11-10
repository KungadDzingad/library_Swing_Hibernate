package com.library;

import com.library.dao.BookDao;
import com.library.exceptions.GhostAccountException;
import com.mysql.cj.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("library");

    private static final BookDao bookDao = new BookDao(ENTITY_MANAGER_FACTORY);

    public static List<Book> initialiseBooks(){
        return bookDao.getAll();
    }

    public static void saveBook(Book book){
        bookDao.save(book);
    }

    public static EntityManager getManager() {
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }

    public static void factoryClose(){
        ENTITY_MANAGER_FACTORY.close();
    }
}
