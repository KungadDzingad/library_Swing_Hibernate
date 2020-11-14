package com.library;

import com.library.exceptions.WrongLoginDataException;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {

    private static List<Book> books;
    private static List<Account> users;

    private static Account loggedUser;

    private final static Scanner scanner = new Scanner(System.in);
    //private final static Scanner scanner 2= new Scanner(Console.readPassword);

    public static void main(String[] args) {
        getData();

        BookItem item = createBookItem(12000000,books.get(1));
        for (Book book : books) {
            for (BookItem bookItem : book.getBookItems()) {
                System.out.println(bookItem.getSignature());
            }
            System.out.println();
        }


        deleteBookItem(item);
        System.out.println("-----------------");

        books = DatabaseConnection.getBooks();

        for (Book book : books) {
            for (BookItem bookItem : book.getBookItems()) {
                if(bookItem.isLended()) {
                    System.out.print(bookItem.getLending().getClient().getLogin());
                    System.out.print(": ");
                    System.out.print(bookItem.getSignature());
                }
            }
            System.out.println("\n");
        }

        DatabaseConnection.factoryClose();
    }

    private static void clientAction(){

    }

    private static void workerAction(){

    }

    private static void getData(){
        books = DatabaseConnection.getBooks();
        users = DatabaseConnection.getAccounts();
    }

    private static Account login() throws WrongLoginDataException {
        System.out.println("Witam, Proszę się zalogować :) : ");
        System.out.print("Login: ");
        String login = scanner.next();
        System.out.print("Password: ");
        String password  = scanner.next();

        boolean foundUser = false;
        for (Account user : users) {
            if(user.getLogin().equals(login) && user.getPassword().equals(password)){
                return user;
            }
        }
        throw new WrongLoginDataException();
    }

    private static Account welcomeAction() {
        Account user = null;
        while (true){
            System.out.println("Witamy:\n1.Zaloguj\t2.Zarejestruj\tinne.Wyjdz");
            int inputVar = scanner.nextInt();

            switch (inputVar) {
                case 1: {
                    try {
                        user = login();
                        System.out.println("Success");
                        break;
                    } catch (WrongLoginDataException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 2: {
                    break;
                }
                default:
                    return null;
            }
            return user;
        }
    }

    private static Book createBook(String title, long isbn, String author, String category, String publisher, int numberOfPages) {
        Book book = new Book(title,isbn,author,category,publisher,numberOfPages);
        DatabaseConnection.saveBook(book);
        return book;
    }

    private static BookItem createBookItem(long signature, Book book){
        BookItem bookItem = new BookItem(signature,book);
        DatabaseConnection.saveBookItem(bookItem);
        return bookItem;
    }

    private static void deleteBookItem(BookItem item){
        for (Book book : books) {
            for (BookItem bookItem : book.getBookItems()) {
                if(bookItem.equals(item))DatabaseConnection.removeBookitem(item);
            }
        }
        books = DatabaseConnection.getBooks();
    }
}
