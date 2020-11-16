package com.library;

import com.library.dao.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class DatabaseConnection {
    private static  final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("library");

//    private static final BookDao BOOK_DAO= new BookDao() ;
//    private static final BookItemDao BOOK_ITEM_DAO = new BookItemDao();
//    private static final BookReservationDao BOOK_RESERVATION_DAO = new BookReservationDao();
//    private static final BookLendingDao BOOK_LENDING_DAO = new BookLendingDao();
//    private static final AccountDao ACCOUNT_DAO = new AccountDao();

    private DatabaseConnection(){}
    public static List<Book> getBooks(){
        return new BookDao().getAll();
    }

    public static List<Account> getAccounts(){
        return new AccountDao().getAll();
    }

    public static void saveBook(Book book){
        new BookDao().save(book);
    }

    public static void saveBookItem(BookItem bookItem){
        new BookItemDao().save(bookItem);
    }

    public static void removeBookitem(BookItem bookItem){
        new BookItemDao().delete(bookItem);
    }

    public static EntityManager getManager() {
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }

    public static void saveBookReservation(BookReservation bookReservation){
        new BookReservationDao().save(bookReservation);
    }

    public static void removeReservation(BookReservation reservation){
        new BookReservationDao().delete(reservation);
    }

    public static  void factoryClose(){
        ENTITY_MANAGER_FACTORY.close();
    }

    public static void saveUser(Account account){
        new AccountDao().save(account);
    }

    public static Account refreshUser(Account user){
        return new AccountDao().refresh(user);
    }

    public static List<Book> refreshBooks(List<Book> books){
        return new BookDao().refresh(books);
    }

    public static List<Account> refreshUsers(List<Account> users){
        return new AccountDao().refresh(users);
    }
}
