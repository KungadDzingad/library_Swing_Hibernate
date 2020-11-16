package com.library;

import com.library.exceptions.BookNotAvailableException;
import com.library.exceptions.WrongLoginDataException;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.util.Scanner;
import java.util.List;

public class LibraryManagementSystem {

    private static  LibraryManagementSystem managementSystem;

    public static LibraryManagementSystem getSystem(){
        if(managementSystem == null){
            managementSystem =  new LibraryManagementSystem();
        }
        return managementSystem;
    }


    private static List<Book> books;
    private static List<Account> users;

    private static Account loggedUser;

    private final static Scanner scanner = new Scanner(System.in);
    //private final static Scanner scanner 2= new Scanner(Console.readPassword);

    private LibraryManagementSystem(){
        loggedUser = null;
        getData();
    }


    private void getData(){
        books = DatabaseConnection.getBooks();
        users = DatabaseConnection.getAccounts();
    }

    private static Account login() throws WrongLoginDataException {
       return null;
    }

    private static Account welcomeAction() {
        return null;
    }

    private Book createBook(String title, long isbn, String author, String category, String publisher, int numberOfPages) {
        Book book = new Book(title,isbn,author,category,publisher,numberOfPages);
        DatabaseConnection.saveBook(book);
        return book;
    }

    private BookItem createBookItem(long signature, Book book){
        BookItem bookItem = new BookItem(signature,book);
        DatabaseConnection.saveBookItem(bookItem);
        return bookItem;
    }

    private void deleteBookItem(BookItem item){
        for (Book book : books) {
            for (BookItem bookItem : book.getBookItems()) {
                if(bookItem.equals(item))DatabaseConnection.removeBookitem(item);
            }
        }
        books = DatabaseConnection.getBooks();
    }

    public void addClientReservation(Book book, Date from, Date to)throws BookNotAvailableException {
        if (loggedUser instanceof Client) {
            BookReservation bookReservation = loggedUser.reserveBook(book,from,to);
            DatabaseConnection.saveBookReservation(bookReservation);
            books = DatabaseConnection.getBooks();
            users = DatabaseConnection.getAccounts();
        }
    }

    public void cancelReservation(BookReservation reservation){
        DatabaseConnection.removeReservation(reservation);
        getData();
    }

    public List<Book> getBooks(){
        return books;
    }

    public Account getLoggedUser(){
        return loggedUser;
    }

    public List<Account> getUsers() {
        return users;
    }

    public boolean loginUser(String login, String password){
        for (Account user : users) {
            if(user instanceof Client){
                if(user.getLogin().equals(login) && user.getPassword().equals(password)) {
                    loggedUser = user;
                    return true;
                }
            }
        }
        return false;
    }
}
