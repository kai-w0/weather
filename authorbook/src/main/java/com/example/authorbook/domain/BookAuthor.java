package com.example.authorbook.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "book_author")
@Entity
public class BookAuthor {

    public BookAuthor(Book book, Author author) {
        this.book = book;
        this.author = author;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "b_id", referencedColumnName = "id")
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "a_id",referencedColumnName = "id")
    private Author author;

    public BookAuthor() {
    }

    public BookAuthor(Long id, Book book, Author author) {
        this.id = id;
        this.book = book;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "BookAuthor{" +
                "id='" + id + '\'' +
                ", book=" + book +
                ", author=" + author +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookAuthor that = (BookAuthor) o;
        return id.equals(that.id) && book.equals(that.book) && author.equals(that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, author);
    }
}
