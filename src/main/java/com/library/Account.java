package com.library;

public abstract class Account {
    private String mail;
    private String login;
    private String password;
    private String name;
    private String lastName;
    private long pesel;

    public Account(String mail, String login, String password, String name, String lastName, long pesel) {
        this.mail = mail;
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.pesel = pesel;
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
