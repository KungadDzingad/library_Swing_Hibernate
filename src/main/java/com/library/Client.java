package com.library;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="client")
@PrimaryKeyJoinColumn(name="account_id")
public class Client  extends Account {

    @Column(name="library_card",nullable = false,unique = true)
    private long libraryCard;

    @OneToMany(mappedBy = "client",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<BookReservation> bookReservations = new ArrayList<>();

    @OneToMany(mappedBy = "client",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<BookLending> bookLendings = new ArrayList<>();

    public Client(String mail, String login, String password, String name, String lastName, long pesel, long libraryCard) {
        super(mail, login, password, name, lastName, pesel);
        this.libraryCard = libraryCard;
        bookReservations = new ArrayList<>();
        bookLendings = new ArrayList<>();
    }

    public Client(){ }

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

    public List<BookReservation> getBookReservations(){
        return bookReservations;
    }
}
