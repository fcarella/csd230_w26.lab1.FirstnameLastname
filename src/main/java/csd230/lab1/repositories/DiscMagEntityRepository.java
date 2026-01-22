package csd230.lab1.repositories;

import csd230.lab1.entities.DiscMagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface DiscMagEntityRepository extends JpaRepository<DiscMagEntity, Long> {


    List<DiscMagEntity> findByTitle(String title);

    List<DiscMagEntity> findByTitleLike(String titlePattern);

    List<DiscMagEntity> findByHasDisc(boolean hasDisc);

    List<DiscMagEntity> findByOrderQtyGreaterThan(int orderQty);


    @Query("SELECT d FROM DiscMagEntity d WHERE d.hasDisc = true AND d.price > :minPrice")
    List<DiscMagEntity> findDiscMagWithDiscAndPriceGreaterThan(
            @Param("minPrice") double minPrice);
}