package com.example.authorbook.domain.dto;

import com.example.authorbook.domain.Book;
import com.example.authorbook.domain.BookAuthor;

import java.util.List;

public class BookDto {

    private Long id;
    private String name;

    private List<BookAuthor> bookAuthors;

    public BookDto() {
    }

    public BookDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.bookAuthors = book.getBookAuthors();
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
