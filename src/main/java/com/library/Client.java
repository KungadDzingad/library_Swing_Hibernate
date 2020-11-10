package com.library;

import java.util.ArrayList;
import java.util.List;

public class Client  extends Account{
    private long libraryCard;
    private List<BookReservation> bookReservations;
    private List<BookLending> bookLendings;


    public Client(String mail, String login, String password, String name, String lastName, long pesel, long libraryCard) {
        super(mail, login, password, name, lastName, pesel);
        this.libraryCard = libraryCard;
        bookReservations = new ArrayList<>();
    }

    public boolean verify() {
        return false;
    }

    public void cancelReservation() {

    }

    public Book searchBook() {
        return null;
    }

    public Book searchBook(String query) {
        return null;
    }

    public long getLibraryCard(){
        return libraryCard;
    }

    public void addReservation(BookReservation res){
        bookReservations.add(res);
    }

    public void addLending(BookLending lending){
        bookLendings.add(lending);
    }
}
