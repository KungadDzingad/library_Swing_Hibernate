package com.library;

import javax.persistence.*;

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
}
