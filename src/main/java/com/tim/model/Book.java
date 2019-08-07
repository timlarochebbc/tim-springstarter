package com.tim.model;

public class Book {
    private long isbn;
    private String title;
    private String author;

    public Book(String title, String author, long isbn){
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    //Default constructor
    private Book(){
        super();
    }

    public long getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
