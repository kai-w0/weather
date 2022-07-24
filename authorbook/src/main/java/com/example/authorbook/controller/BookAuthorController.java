package com.example.authorbook.controller;

import com.example.authorbook.domain.CommonResponse;
import com.example.authorbook.domain.ErrorResponse;
import com.example.authorbook.domain.dto.BookAuthorDto;
import com.example.authorbook.exception.ResourceNotFoundException;
import com.example.authorbook.service.BookAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class BookAuthorController {

    private final BookAuthorService bookAuthorService;

    @Autowired
    public BookAuthorController(BookAuthorService bookAuthorService) {
        this.bookAuthorService = bookAuthorService;
    }

    @GetMapping("/bookauthors")
    public ResponseEntity<CommonResponse> getAllBookAuthors(@RequestParam(required = false) String name) {
        return new ResponseEntity<>(bookAuthorService.getAllBookAuthor(), HttpStatus.OK);
    }

    @GetMapping("/bookauthors/{id}")
    public ResponseEntity<CommonResponse> getBookAuthorById(@PathVariable Long id) {
        return new ResponseEntity<>(bookAuthorService.getBookAuthorById(id), HttpStatus.OK);
    }

    @PostMapping("/bookauthors")
    public ResponseEntity<CommonResponse> createBookAuthor(@RequestBody BookAuthorDto bookAuthorDto) {
        return new ResponseEntity<>(bookAuthorService.createBookAuthor(bookAuthorDto), HttpStatus.CREATED);
    }

    @PutMapping("/bookauthors/{id}")
    public ResponseEntity<CommonResponse> updateBookAuthor(@RequestBody BookAuthorDto bookAuthorDto, @PathVariable Long id) {
        return new ResponseEntity<>(bookAuthorService.updateBookAuthor(id, bookAuthorDto), HttpStatus.OK);
    }

    @DeleteMapping("/bookauthors/{id}")
    public ResponseEntity<CommonResponse> deleteBookAuthor(@PathVariable Long id) {
        return new ResponseEntity<>(bookAuthorService.deleteBookAuthor(id), HttpStatus.OK);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundHandler() {
        return new ResponseEntity<>(new ErrorResponse("this is the message", new Date()), HttpStatus.NOT_FOUND);
    }
}
