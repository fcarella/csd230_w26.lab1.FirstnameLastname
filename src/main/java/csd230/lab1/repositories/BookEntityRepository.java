package csd230.lab1.repositories;

import csd230.lab1.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface BookEntityRepository extends JpaRepository<BookEntity, Long> {

    // Find books by ISBN (inherited field from BookEntity)
    List<BookEntity> findByIsbn(String isbn);

    // Explicit findById using primitive long
    BookEntity findById(long id);

    // Find books by exact title
    List<BookEntity> findByTitle(String title);

    // Find books by part of title (case-insensitive)
    List<BookEntity> findByTitleContainingIgnoreCase(String titlePart);

    // Custom JPQL query using the *field name* "price"
    @Query("SELECT b FROM BookEntity b WHERE b.price BETWEEN :min AND :max")
    List<BookEntity> findBooksByPriceRange(@Param("min") double min,
                                           @Param("max") double max);
}
