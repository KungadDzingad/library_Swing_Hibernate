package com.library;

import com.sun.istack.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="book_item")
public class BookItem implements Serializable {

    @Id
    @Column(name="signature",
            nullable = false,
            unique = true
    )
    private long signature;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="book_id")
    private Book book;

    @OneToMany(mappedBy = "bookItem",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<BookReservation> reservations = new ArrayList<>();

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name="book_lending_id")
    private BookLending lending;

    public BookItem(long signature, Book book) {
        this.signature = signature;
        this.book = book;
        this.reservations = new ArrayList<>();
        this.lending = null;
    }

    public BookItem(){}

    public long getSignature() {
        return signature;
    }

    public Book getBook() {
        return book;
    }

    public List<BookReservation> getReservations() {
        return reservations;
    }

    public boolean isLended(){
        return lending != null;
    }

    public BookLending getLending(){
        return lending;
    }
}
