package com.example.bookstore.controller;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.AuthorRepository;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    // ✅ Pagination + Sorting
    @GetMapping("/page")
    public ResponseEntity<Page<Book>> getBooksPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy.trim()));
        Page<Book> books = bookRepository.findAll(pageable);
        return ResponseEntity.ok(books);
    }

    // ✅ Get all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // ✅ Get book by ID
    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable Long id) {
        return bookRepository.findById(id);
    }

    // ✅ Search / Filter books
    @GetMapping("/search")
    public List<Book> searchBooks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        if (author != null && !author.isBlank()) {
            return bookRepository.findByAuthorNameContainingIgnoreCase(author);
        } else if (minPrice != null && maxPrice != null) {
            return bookRepository.findByPriceBetween(minPrice, maxPrice);
        }
        return bookRepository.findAll();
    }


    // ✅ Create book
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        Long authorId = book.getAuthor().getId();
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found with id " + authorId));
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    // ✅ Update book
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setTitle(bookDetails.getTitle());
        book.setPrice(bookDetails.getPrice());
        book.setAuthor(authorRepository.findById(bookDetails.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("Author not found")));
        return bookRepository.save(book);
    }

    // ✅ Delete book
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "Book with ID " + id + " deleted successfully.";
    }
}

