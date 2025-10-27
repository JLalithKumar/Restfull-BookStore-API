package com.example.bookstore.controller;

//package com.example.bookstore.controller;

import com.example.bookstore.model.Author;
import com.example.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    // Get all authors
    @GetMapping
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    // Get author by ID
    @GetMapping("/{id}")
    public Optional<Author> getAuthorById(@PathVariable Long id) {
        return authorRepository.findById(id);
    }

    // Add author
    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorRepository.save(author);
    }

    // Update author
    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) {
        Author author = authorRepository.findById(id).orElseThrow();
        author.setName(authorDetails.getName());
        author.setNationality(authorDetails.getNationality());
        return authorRepository.save(author);
    }

    // Delete author
    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorRepository.deleteById(id);
        return "Author with ID " + id + " deleted.";
    }
}

