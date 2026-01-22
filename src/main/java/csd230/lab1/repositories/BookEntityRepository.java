package csd230.lab1.repositories;

import csd230.lab1.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BookEntityRepository extends JpaRepository<BookEntity, Long> {

    // Derived queries
    List<BookEntity> findByTitle(String title);


    //
    @Query("SELECT b FROM BookEntity b WHERE b.price BETWEEN :minPrice AND :maxPrice")
    List<BookEntity> findByPriceRange(@Param("minPrice") double minPrice,
                                      @Param("maxPrice") double maxPrice);

    @Query("SELECT b FROM BookEntity b WHERE b.author LIKE %:authorName% AND b.copies > :minCopies")
    List<BookEntity> findByAuthorContainingAndCopiesGreaterThan(
            @Param("authorName") String authorName,
            @Param("minCopies") int minCopies);
}