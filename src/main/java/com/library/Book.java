package com.library;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;


@Entity
@Table(name="book")
public class Book implements Serializable{
    @Id
    @Column(name="isbn",nullable = false, unique = true)
    private long isbn;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="author", nullable = false)
    private String author;

    @Column(name="category", nullable = false)
    private String category;

    @Column(name="publisher", nullable = false)
    private String publisher;

    @Column(name="number_of_pages", nullable = false)
    private int numberOfPages;

    @OneToMany(mappedBy = "book",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<BookItem> bookItems  = new ArrayList<>();

    public Book(String title, long isbn, String author, String category, String publisher, int numberOfPages) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.category = category;
        this.publisher = publisher;
        this.numberOfPages = numberOfPages;

    }

    public Book() {

    }

    public long getIsbn(){
        return isbn;
    }
    public List<BookItem> getBookItems(){
        return bookItems;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }
}
