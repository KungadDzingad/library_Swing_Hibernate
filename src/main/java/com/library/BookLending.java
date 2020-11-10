package com.library;

import java.util.Date;

public class BookLending {
    private int bookLendingId;
    private long booKItemId;
    private long clientId;

    private Date lendedFrom;
    private Date lendedTo;

    public BookLending(int bookLendingId, long booKItemId, long clientId, Date lendedFrom, Date lendedTo) {
        this.bookLendingId = bookLendingId;
        this.booKItemId = booKItemId;
        this.clientId = clientId;
        this.lendedFrom = lendedFrom;
        this.lendedTo = lendedTo;
    }
}
