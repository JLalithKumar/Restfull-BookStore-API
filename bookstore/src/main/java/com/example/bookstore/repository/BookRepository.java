package com.example.bookstore.repository;

//package com.example.bookstore.repository;
import java.util.List;
import com.example.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Find books by author's name (case-insensitive, partial match)
    List<Book> findByAuthorNameContainingIgnoreCase(String author);

    // Find books within a price range
    List<Book> findByPriceBetween(Double minPrice, Double maxPrice);
}
