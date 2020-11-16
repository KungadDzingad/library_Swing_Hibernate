package com.library;

import com.library.exceptions.BookNotAvailableException;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name="client")
@PrimaryKeyJoinColumn(name="account_id")
public class Client  extends Account {


    @Column(name="library_card",nullable = false,unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Client(String mail, String login, String password, String name, String lastName, long pesel) {
        super(mail, login, password, name, lastName, pesel);
        bookReservations = new ArrayList<>();
        bookLendings = new ArrayList<>();
    }

    public Client(){ }

    public boolean verify() {
        return false;
    }

    @Override
    public BookReservation reserveBook(Book book, Date from, Date to) throws BookNotAvailableException{
        boolean[] canReserve = new boolean[book.getBookItems().size()];

        int i = 0;
        for (BookItem bookItem : book.getBookItems()) {
            canReserve[i] = true;
            for (BookReservation reservation : bookItem.getReservations()) {
                if (reservation.getFrom().compareTo(to) < 0 || reservation.getTo().compareTo(from) > 0) {
                    canReserve[i] = false;
                    break;
                }
            }
        }
        for (int j = 0; j < canReserve.length; j++) {
            if (canReserve[j]) {
                BookItem bookItem = book.getBookItems().get(j);
                BookReservation reservation = new BookReservation(from, to, bookItem, this);
                return reservation;
            }
        }
        throw new BookNotAvailableException();
    }

    @Override
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
