package com.library;

import java.util.Date;

public class BookReservation {
    private long bookItemId;
    private long clientId;

    private Date dateFrom;
    private Date dateTo;

    public BookReservation(long bookItemId, long clientId, Date dateFrom, Date dateTo) {
        this.bookItemId = bookItemId;
        this.clientId = clientId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}
