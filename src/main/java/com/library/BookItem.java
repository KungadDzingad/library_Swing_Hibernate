package com.library;

import java.util.ArrayList;
import java.util.List;

public class BookItem {
    private long signature;
    private BookLending lending;
    private List<BookReservation> bookReservations;

    public BookItem(long signature) {
        this.signature = signature;
        this.lending = null;
        this.bookReservations = new ArrayList<>();
    }

    public void lend(BookLending lending){
        this.lending = lending;
    }

    public boolean isLended(){
        return lending != null;
    }

    public long getSignature(){
        return signature;
    }

    public void addReservation(BookReservation res){
        bookReservations.add(res);
    }

    public void setLending(BookLending lending){
        if(lending == null)
            this.lending = lending;
    }

    public List<BookReservation> getBookReservations(){
        return bookReservations;
    }
}
