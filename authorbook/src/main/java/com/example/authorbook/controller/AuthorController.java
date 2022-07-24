package com.example.authorbook.controller;

import com.example.authorbook.domain.CommonResponse;
import com.example.authorbook.domain.ErrorResponse;
import com.example.authorbook.domain.dto.AuthorPostDto;
import com.example.authorbook.exception.ResourceNotFoundException;
import com.example.authorbook.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public ResponseEntity<CommonResponse> getAllAuthors(@RequestParam(required = false) String name) {
        return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<CommonResponse> getAuthorById(@PathVariable Long id) {
        return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
    }

    @PostMapping("/authors")
    public ResponseEntity<CommonResponse> createAuthor(@RequestBody AuthorPostDto authorPostDto) {
        return new ResponseEntity<>(authorService.createAuthor(authorPostDto), HttpStatus.CREATED);
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<CommonResponse> updateAuthor(@RequestBody AuthorPostDto authorPostDto, @PathVariable Long id) {
        return new ResponseEntity<>(authorService.updateAuthor(id, authorPostDto), HttpStatus.OK);
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<CommonResponse> deleteAuthor(@PathVariable Long id) {
        return new ResponseEntity<>(authorService.deleteAuthor(id), HttpStatus.OK);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundHandler() {
        return new ResponseEntity<>(new ErrorResponse("this is the message", new Date()), HttpStatus.NOT_FOUND);
    }
}
