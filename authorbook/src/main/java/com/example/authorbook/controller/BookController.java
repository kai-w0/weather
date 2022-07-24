package com.example.authorbook.controller;

import com.example.authorbook.domain.CommonResponse;
import com.example.authorbook.domain.ErrorResponse;
import com.example.authorbook.domain.dto.BookDto;
import com.example.authorbook.exception.ResourceNotFoundException;
import com.example.authorbook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<CommonResponse> getAllBooks(@RequestParam(required = false) String name) {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<CommonResponse> getBookById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<CommonResponse> createBook(@RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookService.createBook(bookDto), HttpStatus.CREATED);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<CommonResponse> updateBook(@RequestBody BookDto bookDto, @PathVariable Long id) {
        return new ResponseEntity<>(bookService.updateBook(id, bookDto), HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<CommonResponse> deleteBook(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.deleteBook(id), HttpStatus.OK);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundHandler() {
        return new ResponseEntity<>(new ErrorResponse("this is the message", new Date()), HttpStatus.NOT_FOUND);
    }
}
