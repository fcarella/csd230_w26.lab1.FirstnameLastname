package csd230.lab1.repositories;

import csd230.lab1.entities.BookEntity;
import csd230.lab1.pojos.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    Book findById(long id);

    @Query("SELECT b FROM BookEntity b WHERE b.price BETWEEN :minPrice AND :maxPrice")
    List<BookEntity> findByPriceRange(@Param("minPrice") double minPrice,
                                      @Param("maxPrice") double maxPrice);



}