package com.example.bookstore.model;

//package com.example.bookstore.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;             // for @JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference;  // for parent side
import com.fasterxml.jackson.annotation.JsonBackReference;     // for child side

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String nationality;

    // One author can have many books
    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<Book> books;

    // Constructors
    public Author() {}

    public Author(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    // Getters & Setters
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

