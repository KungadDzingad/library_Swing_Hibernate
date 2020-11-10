package com.library;

import com.library.exceptions.GhostAccountException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibraryManagementSystem {

    private static List<Book> books;
    private static List<Client> clients;
    private static List<Worker> workers;

    private static void getData(){
        try {

            DatabaseConnection.inflateUsers(clients, workers);
            books = DatabaseConnection.getBooks();
            DatabaseConnection.inflateLendings(books,clients);
            DatabaseConnection.inflateReservations(books,clients);

        } catch (GhostAccountException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        clients = new ArrayList<>();
        workers = new ArrayList<>();
        getData();

        for (Account client : clients) {
            System.out.println(client.getMail());
        }
    }


}
