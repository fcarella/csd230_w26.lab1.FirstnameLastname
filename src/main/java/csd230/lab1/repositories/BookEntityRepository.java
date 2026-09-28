package csd230.lab1.repositories;

import csd230.lab1.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing BookEntity database operations.
 */
@Repository
public interface BookEntityRepository extends JpaRepository<BookEntity, Long> {

    /**
     * Derived query to find books by their ISBN.
     *
     * @param isbn The ISBN of the book
     * @return List of matching BookEntities
     */
    List<BookEntity> findByIsbn(String isbn);

    /**
     * Derived query to find books whose title contains a specific pattern.
     *
     * @param title The title search pattern
     * @return List of matching BookEntities
     */
    List<BookEntity> findByTitleLike(String title);

    /**
     * Custom JPQL query to find books within a specific price range.
     *
     * @param minPrice Minimum price
     * @param maxPrice Maximum price
     * @return List of books within the given price range
     */
    @Query("SELECT b FROM BookEntity b WHERE b.price >= :minPrice AND b.price <= :maxPrice")
    List<BookEntity> findByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
}