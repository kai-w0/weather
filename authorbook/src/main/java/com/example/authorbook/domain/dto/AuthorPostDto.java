package com.example.authorbook.domain.dto;

import com.example.authorbook.domain.Author;
import com.example.authorbook.domain.BookAuthor;

import java.util.List;

public class AuthorPostDto {

    private Long id;
    private String name;

    private List<BookAuthor> bookAuthors;

    public AuthorPostDto() {
    }

    public AuthorPostDto(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        //this.bookAuthors = author.getBookAuthors();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookAuthor> getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(List<BookAuthor> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }
}
