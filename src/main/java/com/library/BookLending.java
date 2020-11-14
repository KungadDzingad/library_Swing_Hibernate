package com.library;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="book_lending")
public class BookLending  implements Serializable {

    @Id
    @Column(name="book_lending_id")
    private long id;

    @Column(name="lended_from")
    @Temporal(TemporalType.DATE)
    private Date from;

    @Column(name="lended_to")
    @Temporal(TemporalType.DATE)
    private Date to;

    @OneToOne(mappedBy ="lending",cascade = CascadeType.ALL,optional = false)
    private BookItem bookItem;

    @ManyToOne
    @JoinColumn(name="client_id", referencedColumnName = "library_card")
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

    public Client getClient() {
        return client;
    }
}
