package com.tim.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class BookStub {
    // Map ISBN to a Book
    private Map<Long, Book> library;
    private String message;

    public BookStub(){
        this.library = new HashMap<>();
        library = new HashMap<Long, Book>();
        Book book1 = new Book("Hungry Caterpillar", "Eric Carle", 9788954913072L);
        library.put(book1.getIsbn(), book1);
        Book book2 = new Book("Dracula", "Bram Stoker", 9781623750282L);
        library.put(book2.getIsbn(), book2);
        Book book3 = new Book("Frankenstein", "Mary Shelley",9781502371065L);
        library.put(book3.getIsbn(), book3);
        Book book4 = new Book("Harry Potter", "JK Rowling", 9788700631625L);
        library.put(book4.getIsbn(), book4);
        Book book5 = new Book("Great Expectations", "Charles Dickens", 9789633982297L);
        library.put(book5.getIsbn(), book5);

        System.out.println(message);
    }

    public BookStub(Map<Long, Book> customLibrary){
        this.library = customLibrary;
    }

    public Map<Long, Book> getLibrary() {
        return library;
    }

    public void setLibrary(Map<Long, Book> library) {
        this.library = library;
    }

    public Map<Long, Book> getAll(){
        return library;
    }

    public Book getBook(long isbn){
        return library.get(isbn);
    }

    public Book addBook(Book book){
        return library.put(book.getIsbn(), book);
    }

    public Book updateBook(Long isbn, Book book){
        return library.replace(isbn, book);
    }

    public boolean deleteBook(long isbn){
        try{
            library.remove(isbn);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
