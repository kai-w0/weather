package com.example.authorbook.domain.dto;

import com.example.authorbook.domain.Author;
import com.example.authorbook.domain.Book;
import com.example.authorbook.domain.BookAuthor;

public class BookAuthorDto {

    private Long id;
    private Author author;
    private Book book;

    public BookAuthorDto() {
    }

    public BookAuthorDto(BookAuthor bookAuthor) {
        this.id = bookAuthor.getId();
        this.author = bookAuthor.getAuthor();
        this.book = bookAuthor.getBook();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
