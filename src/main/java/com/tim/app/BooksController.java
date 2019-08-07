package com.tim.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tim.model.Book;
import com.tim.model.BookStub;
import com.tim.model.BooksControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BooksController implements BooksControllerInterface {

    ObjectMapper objectMapper;
    BookStub library;

    public BooksController(ObjectMapper objectMapper, BookStub library){
        System.out.println("We have made a books controller");
        this.objectMapper = objectMapper;
        this.library = library;
    }

    public ResponseEntity<String> books(){
        try{
            System.out.println("we tried");
            String jsonResponse = objectMapper.writeValueAsString(library);
            ResponseEntity<String> response = new ResponseEntity<String>(jsonResponse, HttpStatus.OK);
            return response;
        }catch(Exception e){
            System.out.println("We failed");
            return new ResponseEntity<>("Unable to retrieve books.", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> oneBook(long isbn){
        try{
            //Gets object associated with key and serialises it as a string.
            String jsonResponse = objectMapper.writeValueAsString(library.getBook(isbn));
            return new ResponseEntity<String>(jsonResponse, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<String>("Unable to find book.", HttpStatus.NO_CONTENT);
        }
    }

    //Takes JSON string in the request body as raw  data.
    public ResponseEntity<String> addBook(HttpEntity<String> httpEntity){
        //Hacky way to get JSON information from the body. This will be the JSON for the book.
        String bookJson = httpEntity.getBody();
        try {
            Book newBook = objectMapper.readValue(bookJson, Book.class);
            return new ResponseEntity<String>("Successfully added book, ISBN: " + newBook.getIsbn(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<String>("Unable to add book.", HttpStatus.BAD_REQUEST);
        }
    }

    //Similarly to addBook, takes a JSON string in the request body as raw data.
    public ResponseEntity<String> updateBook(long isbn, HttpEntity<String> httpEntity){
        String bookJson = httpEntity.getBody();
        try{
            Book book = objectMapper.readValue(bookJson, Book.class);
            Book updatedBook = library.updateBook(isbn, book);
            return new ResponseEntity<String>("Successfully updated book, ISBN: " + updatedBook.getIsbn(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<String>("Unable to update book.", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteBook(long isbn){
        try{
            library.deleteBook(isbn);
            return new ResponseEntity<String>("Successfully deleted book, ISBN: " + isbn, HttpStatus.OK);
        }catch(Exception e){
            System.out.println("Unable to delete book: " + e.toString());
            return new ResponseEntity<String>("Unable to delete book.", HttpStatus.BAD_REQUEST);
        }
    }
}
