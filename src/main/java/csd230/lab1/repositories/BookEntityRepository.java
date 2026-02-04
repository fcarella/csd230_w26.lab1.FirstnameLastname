//package csd230.lab1.repositories;
//
//import csd230.lab1.entities.BookEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface BookEntityRepository extends JpaRepository<BookEntity, Long> {
//
//    // Derived queries
//    List<BookEntity> findByIsbn(String isbn);          // you must add isbn in BookEntity
//    Optional<BookEntity> findFirstByIsbn(String isbn);
//    BookEntity findById(long id);                      // NOTE: JpaRepository already has findById(Long) returning Optional
//
//    List<BookEntity> findByTitle(String title);
//    List<BookEntity> findByTitleLike(String pattern);  // e.g. "%drew%"
//
//    // Custom @Query (price range)
//    @Query("SELECT b FROM BookEntity b WHERE b.price BETWEEN :min AND :max")
//    List<BookEntity> findBooksInPriceRange(@Param("min") double min,
//                                           @Param("max") double max);
//}
package csd230.lab1.repositories;

import csd230.lab1.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookEntityRepository extends JpaRepository<BookEntity, Long> {

    // Derived queries
    List<BookEntity> findByIsbn(String isbn);

    // If ISBN is unique, you can prefer Optional:
    Optional<BookEntity> findFirstByIsbn(String isbn);

    List<BookEntity> findByTitle(String title);

    // Easier than Like: auto does %text%
    List<BookEntity> findByTitleContainingIgnoreCase(String text);

    // Custom @Query (price range)
    @Query("SELECT b FROM BookEntity b WHERE b.price BETWEEN :min AND :max")
    List<BookEntity> findBooksInPriceRange(@Param("min") double min,
                                           @Param("max") double max);
}
