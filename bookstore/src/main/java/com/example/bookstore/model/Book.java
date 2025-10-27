package com.example.bookstore.model;
//package com.example.bookstore.model;

import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private double price;

    // Many books belong to one author
    @ManyToOne
    @JoinColumn(name = "author_id")
  //  @JsonBackReference
    private Author author;

    // Constructors
    public Book() {}

    public Book(String title, double price, Author author) {
        this.title = title;
        this.price = price;
        this.author = author;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
