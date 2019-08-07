package com.tim.app;
import com.tim.model.Book;
import static org.junit.Assert.*;

import com.tim.model.BookStub;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class BookStubTest {
    BookStub underTest;

    String message;

    @Before
    public void beforeTests(){
        underTest = new BookStub();
    }

    @Test
    public void getBookTest(){
        Book book = underTest.getBook(9781502371065L);

        assertEquals(book.getIsbn(), 9781502371065L);
        assertEquals(book.getTitle(), "Frankenstein");
        assertEquals(book.getAuthor(), "Mary Shelley");

    }

    @Test
    public void getAllTest(){
        assertEquals(underTest.getAll().size(),5);
    }

    @Test
    public void getNullBookTest(){
        Book nullBook = underTest.getBook(1111111111L);
        assertEquals(nullBook, null);
    }

    @Test
    public void addBook(){
        underTest.addBook(new Book("Intro to Java", "Java Author", 1111111111L));
        Book newBook = underTest.getBook(1111111111L);

        assertEquals(newBook.getIsbn(), 1111111111L);
        assertEquals(newBook.getTitle(), "Intro to Java");
        assertEquals(newBook.getAuthor(), "Java Author");

        assertEquals(underTest.getAll().size(), 6);
    }

    @Test
    public void updateBook(){
        getBookTest();
        Book updatedBook = new Book("Frankenstein 2: The Sequel", "Mary Berry", 9781502371065L);
        underTest.updateBook(9781502371065L, updatedBook);
        updatedBook = underTest.getBook(9781502371065L);

        assertEquals(updatedBook.getIsbn(), 9781502371065L);
        assertEquals(updatedBook.getAuthor(), "Mary Berry");
        assertEquals(updatedBook.getTitle(), "Frankenstein 2: The Sequel");
    }

    @Test
    public void updateNullBookTest(){
        getNullBookTest();
        Book updatedNullBook = new Book("Doesn't Matter Anyways", "Author1", 1111111111L);
        underTest.updateBook(1111111111L, updatedNullBook);
        updatedNullBook = underTest.getBook(1111111111L);

        assertEquals(null, updatedNullBook);
    }

    @Test
    public void deleteBookTest(){
        getBookTest();
        underTest.deleteBook(9781502371065L);
        Book deletedBook = underTest.getBook(9781502371065L);

        assertEquals(deletedBook, null);
        assertEquals(underTest.getAll().size(), 4);
    }
}
