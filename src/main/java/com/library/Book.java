package com.library;

import java.util.List;

public class Book {
    private String title;
    private long isbn;
    private String author;
    private String category;
    private String publisher;
    private int numberOfPages;

    private List<BookItem> bookItems;

    public Book(String title, long isbn, String author, String category, String publisher, int numberOfPages, List<BookItem> bookItems) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.category = category;
        this.publisher = publisher;
        this.numberOfPages = numberOfPages;
        this.bookItems = bookItems;
    }

    public List<BookItem> getBookItems(){
        return bookItems;
    }
    public long getIsbn(){
        return isbn;
    }
}
