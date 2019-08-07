package com.tim.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tim.model.Book;
import com.tim.model.BookStub;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource(value="app.properties")
public class AppConfig {

//    @Value("${library}")
    private Map<Long, Book> library = new HashMap<>();

    @Value("${msg}")
    private String message;

    @Bean
    public BookStub bookStubBean(){
        System.out.println("FROM BOOK STUB: " + message);
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
        BookStub bookStub = new BookStub(library);
        return bookStub;
    }

    @Bean
    public ObjectMapper objectMapperBean(){
        System.out.println("FROM OM:" + message);
        return new ObjectMapper();
    }

}