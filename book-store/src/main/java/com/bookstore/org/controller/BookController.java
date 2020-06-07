package com.bookstore.org.controller;

import com.bookstore.org.exception.ResourceNotFoundException;
import com.bookstore.org.model.Book;
import com.bookstore.org.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        LOGGER.debug("REST request to get all Books : {}");
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Integer bookId)
            throws ResourceNotFoundException {
        LOGGER.debug("REST request to get Book : {}", bookId);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + bookId));
        return ResponseEntity.ok().body(book);
    }

    @PostMapping("/Books")
    public Book createBook(@Valid @RequestBody Book book) {
        LOGGER.debug("REST request to save Book : {}", book);
        return bookRepository.save(book);
    }

    @PutMapping("/Books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable(value = "id") Integer bookId,
                                               @Valid @RequestBody Book bookDetails) throws ResourceNotFoundException {
        LOGGER.debug("REST request to update Book : {}", bookId);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + bookId));

        book.setCategory(bookDetails.getCategory());
        book.setTitle(bookDetails.getTitle());
        book.setYear(bookDetails.getYear());
        book.setPrice(bookDetails.getPrice());
        final Book updatedBook = bookRepository.save(book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/books/{id}")
    public Map<String, Boolean> deleteBook(@PathVariable(value = "id") Integer bookId)
            throws ResourceNotFoundException {
        LOGGER.debug("REST request to delete Book : {}", bookId);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + bookId));

        bookRepository.delete(book);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}