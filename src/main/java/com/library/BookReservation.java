package com.library;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="book_reservation")
public class BookReservation  implements Serializable {

    @Id
    @Column(name="book_reservation_id",unique = true)
    private long id;

    @Column(name="date_from")
    @Temporal(TemporalType.DATE)
    private Date from;

    @Column(name="date_to")
    @Temporal(TemporalType.DATE)
    private Date to;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="book_item_id")
    private BookItem bookItem;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id",referencedColumnName = "library_card")
    private Client client;


    public long getId() {
        return id;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

    public BookItem getBookItem() {
        return bookItem;
    }

}
