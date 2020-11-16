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

    private void deleteBookItem(BookItem item){
        for (Book book : books) {
            for (BookItem bookItem : book.getBookItems()) {
                if(bookItem.equals(item))DatabaseConnection.removeBookitem(item);
            }
        }
        refreshData();
    }

    public void addClientReservation(Book book, Date from, Date to)throws BookNotAvailableException {
        if (loggedUser instanceof Client) {
            BookReservation bookReservation = loggedUser.reserveBook(book,from,to);
            DatabaseConnection.saveBookReservation(bookReservation);
            refreshData();
        }
    }

    public void cancelReservation(BookReservation reservation){
        DatabaseConnection.removeReservation(reservation);
        refreshData();
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

    public boolean loginWorker(String login, String password){
        for (Account user : users) {
            if(user instanceof Worker){
                if(user.getLogin().equals(login) && user.getPassword().equals(password)) {
                    loggedUser = user;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean registerClient(String mail, String login, String password, String name, String lastName, long pesel){
        for (Account user : users) {
            if(user.getMail().equals(mail))
                return false;
        }
        Client client = new Client(mail,login,password,name,lastName,pesel);
        try {
            DatabaseConnection.saveUser(client);
            users.add(client);
            refreshData();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public void addNewBook(long isbn,String title, String author, String category, String publisher, int numberOfPages, int howManyBookItems){
        boolean bookExists = false;
        for (Book book : books) {
            if(book.getIsbn() == isbn){
                book.addBookItems(howManyBookItems);
                bookExists = true;
                break;
            }
        }
        if(!bookExists){
            try {
                Book book = new Book(isbn, title, author, category, publisher, numberOfPages, howManyBookItems);
                DatabaseConnection.saveBook(book);
                books.add(book);
            } catch (Exception ignored) { }
        }
        refreshData();
    }

    public void logout(){
        loggedUser = null;
    }

    private void refreshData(){
        if(loggedUser != null)
            loggedUser = DatabaseConnection.refreshUser(loggedUser);
        books = DatabaseConnection.refreshBooks(books);
        users = DatabaseConnection.refreshUsers(users);
        //getData();
    }
}
