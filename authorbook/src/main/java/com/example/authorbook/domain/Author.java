package com.example.authorbook.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "author")
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY , cascade = CascadeType.ALL, orphanRemoval = false)
    private List<BookAuthor> bookAuthors = new ArrayList<>();

    public List<BookAuthor> getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(List<BookAuthor> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }

    public void addBookAuthor(BookAuthor ba) {
        this.bookAuthors.add(ba);
    }

    public Author() {
    }

    public Author(Long id, String name, List<BookAuthor> bookAuthors) {
        this.id = id;
        this.name = name;
        this.bookAuthors = bookAuthors;
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

    @Override
    public String toString() {
        return "Author{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id.equals(author.id) && name.equals(author.name) && bookAuthors.equals(author.bookAuthors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bookAuthors);
    }
}
