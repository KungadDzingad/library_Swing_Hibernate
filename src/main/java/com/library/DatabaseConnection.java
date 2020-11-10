package com.library;

import com.library.exceptions.GhostAccountException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {



    private static Connection connection;
    private final static String DATABASE = "jdbc:mysql://localhost:3306/library";
    private final static String USER = "librarian";
    private final static String PASS = "wzorceprojektowe";

    // Funkcja z opisem dla Bartusia ;3 - zalatwie od razu pobieranie klientow i pracownikow
    public static void inflateUsers(List<Client> clients, List<Worker> workers) throws GhostAccountException {
        try {
            //uzyskiwanie polaczenia z baza danych Bartusiu
            connection = DriverManager.getConnection(DATABASE,USER,PASS);

            //Tworzenie statementu
            Statement statement  = connection.createStatement();

            //pobieram to co zwroci zapytanie ...
            ResultSet accountResult = statement.executeQuery("SELECT * FROM account");

            //przechodzi po "obiektach" z wyniku
            while(accountResult.next()){
                String mail = accountResult.getString("mail");
                String login = accountResult.getString("login");
                String password = accountResult.getString("password");
                String name = accountResult.getString("name");
                String lastName = accountResult.getString("last_name");
                long pesel = accountResult.getLong("pesel");

                //Tutaj Bartek zeby z parametrami zrobic query trzeba zrobic specjalny statement
                String query = "SELECT library_card FROM client WHERE `account_id`=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,mail);

                ResultSet clientResult = preparedStatement.executeQuery();
                if (clientResult.first()){
                    long libraryCard = clientResult.getLong("library_card");
                    clients.add(new Client(mail,login,password,name,lastName,pesel,libraryCard));

                }else{
                    query = "SELECT worker_id FROM worker WHERE `account_id`=?";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1,mail);

                    ResultSet workerResult = preparedStatement.executeQuery();

                    // jesli nie konto bedzie bez pracownika ani klienta wywali blad
                    if(!workerResult.first()) throw new GhostAccountException();

                    long workerId = workerResult.getLong("worker_id");
                    workers.add(new Worker(mail,login,password,name,lastName,pesel,workerId));
                }
            }

            System.out.println("Connecteed !");
            connection.close();
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    public static List<Book> getBooks(){
        List<Book> books = null;

        try {
            connection = DriverManager.getConnection(DATABASE,USER,PASS);
            Statement statement = connection.createStatement();
            ResultSet bookSet = statement.executeQuery("SELECT * FROM book");

            while(bookSet.next()){
                long isbn = bookSet.getLong("isbn");
                String title = bookSet.getString("title");
                String author = bookSet.getString("author");
                String category = bookSet.getString("");
                String publisher = bookSet.getString("publisher");
                int numberOfPages = bookSet.getInt("number_of_pages");

                List<BookItem> bookItems = getBookItems(isbn);
                if(bookItems == null)
                    continue;
                books.add(new Book(title,isbn,author,category,publisher,numberOfPages,bookItems));

            }

            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return books;
    }

    private static List<BookItem> getBookItems(long isbn) throws SQLException {
        List<BookItem> bookItems= null;
        String query = "SELECT signature FROM book_item WHERE book_id=?";
        PreparedStatement bookItemStatement = connection.prepareStatement(query);
        bookItemStatement.setLong(1,isbn);

        ResultSet bookItemSet = bookItemStatement.executeQuery();

        boolean notEmpty = false;
        while(bookItemSet.next()){
            notEmpty = true;


            long signature = bookItemSet.getLong("signature");

        }
        if (!notEmpty)
            return null;

        return bookItems;
    }

    public static void inflateReservations(List<Book> books, List<Client> clients) throws SQLException {
        connection = DriverManager.getConnection(DATABASE, USER, PASS);
        Statement statement = connection.createStatement();
        ResultSet reservationSet = statement.executeQuery("SELECT * FROM book_reservation");
        while(reservationSet.next()){
            long bookItem = reservationSet.getLong("book_item_id");
            long client = reservationSet.getLong("client_id");
            Date from = reservationSet.getDate("date_from");
            Date to = reservationSet.getDate("date_to");
            BookReservation reservation = new BookReservation(bookItem,client,from,to);
            for (Book book : books) {
                for (BookItem item : book.getBookItems()) {
                    if(item.getSignature() == bookItem) {
                        item.addReservation(reservation);
                        break;
                    }
                }
            }
            for (Client client1 : clients) {
                if(client1.getLibraryCard() == client){
                    client1.addReservation(reservation);
                    break;
                }
            }
        }

        connection.close();
    }

    public static void inflateLendings(List<Book> books, List<Client> clients) throws SQLException{
        connection = DriverManager.getConnection(DATABASE, USER, PASS);
        Statement statement = connection.createStatement();
        ResultSet lendingSet = statement.executeQuery("SELECT * FROM book_lending");
        while(lendingSet.next()){
            int bookLendingId = lendingSet.getInt("book_lending_id");
            long bookItem = lendingSet.getLong("book_item_id");
            long client = lendingSet.getLong("client_id");
            Date from = lendingSet.getDate("date_from");
            Date to = lendingSet.getDate("date_to");
            BookLending lending = new BookLending(bookLendingId,bookItem, client, from, to);
            for (Book book : books) {
                for (BookItem item : book.getBookItems()) {
                    if(item.getSignature() == bookItem) {
                        item.setLending(lending);
                        break;
                    }
                }
            }
            for (Client client1 : clients) {
                if(client1.getLibraryCard() == client){
                    client1.addLending(lending);
                    break;
                }
            }
        }

        connection.close();
    }


}
