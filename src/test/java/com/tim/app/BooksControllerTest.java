package com.tim.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tim.model.Book;
import com.tim.model.BookStub;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BooksControllerTest {

    @Mock private ObjectMapper objectMapper;
    @Mock private BookStub testLibrary;
    private String message;
    private BooksController underTest;
    private String serialisedLibrary;


    @Before
    public void before() {
        Book testBook = new Book("Harry Potter", "JK Rowling", 9788700631625L);
        Book testBook2 = new Book("Great Expectations", "Charles Dickens", 9789633982297L);
        this.testLibrary = new BookStub();
        testLibrary.addBook(testBook);
        testLibrary.addBook(testBook2);
        this.underTest = new BooksController(objectMapper, testLibrary);
        serialisedLibrary = "{\\\"9788700631625\\\":{\\\"isbn\\\":9788700631625,\\\"title\\\":\\\"Harry Potter\\\",\\\"author\\\":\\\"JK Rowling\\\"},\\\"9789633982297\\\":{\\\"isbn\\\":9789633982297,\\\"title\\\":\\\"Great Expectations\\\",\\\"author\\\":\\\"Charles Dickens\\\"}}";
    }

    @Test
    public void testBooks() throws IOException {
        // Given
        BooksController underTest = new BooksController(objectMapper, testLibrary);
        given(objectMapper.writeValueAsString(testLibrary)).willReturn(serialisedLibrary);

        // When
        //String books = BookStub.books();
        ResponseEntity<String> response = underTest.books();

        // Then
        verify(objectMapper).writeValueAsString(testLibrary);
        assertEquals(200, response.getStatusCodeValue());
        //check is json string is correct
        assertEquals(serialisedLibrary, response.getBody());
    }

    /*
    @Test
    public void testNoBooks() throws JsonProcessingException {
        underTest = new BooksController(objectMapper, null);
        given(objectMapper.writeValueAsString(null)).willThrow(new BooksControllerTestHelper("Unable"));

        ResponseEntity<String> response = underTest.books();

        verify(objectMapper).writeValueAsString(null);
        assertEquals(404, response.getStatusCodeValue());
    }
    */


    @Test
    public void testOneBook() throws IOException {
        given(objectMapper.writeValueAsString(testLibrary.getBook(9788700631625L))).willReturn("{\"isbn\":9788700631625,\"title\":\"Harry Potter\",\"author\":\"JK Rowling\"}");

        ResponseEntity<String> response = underTest.oneBook(9788700631625L);

        verify(objectMapper).writeValueAsString(testLibrary.getBook(9788700631625L));
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("{\"isbn\":9788700631625,\"title\":\"Harry Potter\",\"author\":\"JK Rowling\"}", response.getBody());
    }


}