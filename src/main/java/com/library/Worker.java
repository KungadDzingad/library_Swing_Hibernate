package com.library;

import com.library.exceptions.BookNotAvailableException;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="worker")
@PrimaryKeyJoinColumn(name="account_id")
public class Worker extends Account{

    @Column(name="worker_id",nullable = false)
    private long workerID;

    public Worker(String mail, String login, String password, String name, String lastName, long pesel, long workerID) {
        super(mail, login, password, name, lastName, pesel);
        this.workerID = workerID;
    }

    public Worker(){}



    @Override
    public BookReservation reserveBook(Book book, Date from, Date to) throws BookNotAvailableException {
        return null;
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

    public long getWorkerID() {
        return workerID;
    }
}
