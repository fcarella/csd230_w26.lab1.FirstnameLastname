package csd230.lab1.repositories;

import csd230.lab1.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findByIsbn(String isbn);

    // Lab requirement (JpaRepository also provides Optional<BookEntity> findById(Long))
    BookEntity findById(long id);

    // "Like" style derived query
    List<BookEntity> findByTitleContaining(String titlePart);

    // Custom @Query example: books within a price range
    @Query("SELECT b FROM BookEntity b WHERE b.price BETWEEN :min AND :max")
    List<BookEntity> findBooksInPriceRange(@Param("min") double min, @Param("max") double max);
}
