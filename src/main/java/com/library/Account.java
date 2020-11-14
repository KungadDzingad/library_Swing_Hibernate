package com.library;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account implements Serializable {
    @Id
    @Column(name="mail",nullable = false)
    private String mail;

    @Column(name="login",nullable = false)
    private String login;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="last_name",nullable = false)
    private String lastName;

    @Column(name="pesel",nullable = false)
    private long pesel;

    public Account(String mail, String login, String password, String name, String lastName, long pesel) {
        this.mail = mail;
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.pesel = pesel;
    }

    public Account(){

    }

    public abstract boolean verify();
    public abstract void cancelReservation();
    public abstract Book searchBook();
    public abstract Book searchBook(String query);

    public String getMail() {
        return mail;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public long getPesel() {
        return pesel;
    }
}
