package com.tim.model;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public interface BooksControllerInterface {
    @RequestMapping("/books")
    public ResponseEntity<String> books();
    @RequestMapping("/books/{isbn}")
    public ResponseEntity<String> oneBook(@PathVariable("isbn") long isbn);
    @PostMapping("/addbook")
    public ResponseEntity<String> addBook(HttpEntity<String> httpEntity);
    @PutMapping("/updatebook/{isbn}")
    public ResponseEntity<String> updateBook(@PathVariable("isbn") long isbn, HttpEntity<String> httpEntity);
    @DeleteMapping("/deletebook/{isbn}")
    public ResponseEntity<String> deleteBook(@PathVariable("isbn") long isbn);
}
