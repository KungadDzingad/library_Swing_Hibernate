package com.library;

import com.library.dao.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DatabaseConnection {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("library");

    private static final BookDao BOOK_DAO = new BookDao(ENTITY_MANAGER_FACTORY);
    private static final BookItemDao BOOK_ITEM_DAO = new BookItemDao(ENTITY_MANAGER_FACTORY);
    private static final BookReservationDao BOOK_RESERVATION_DAO = new BookReservationDao(ENTITY_MANAGER_FACTORY);
    private static final BookLendingDao BOOK_LENDING_DAO = new BookLendingDao(ENTITY_MANAGER_FACTORY);
    private static final AccountDao ACCOUNT_DAO = new AccountDao(ENTITY_MANAGER_FACTORY);

    public static List<Book> getBooks(){
        return BOOK_DAO.getAll();
    }

    public static List<Account> getAccounts(){
        return ACCOUNT_DAO.getAll();
    }

    public static void saveBook(Book book){
        BOOK_DAO.save(book);
    }

    public static void saveBookItem(BookItem bookItem){
        BOOK_ITEM_DAO.save(bookItem);
    }

    public static void removeBookitem(BookItem bookItem){
        BOOK_ITEM_DAO.delete(bookItem);
    }

    public static EntityManager getManager() {
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }

    public static void factoryClose(){
        ENTITY_MANAGER_FACTORY.close();
    }
}
