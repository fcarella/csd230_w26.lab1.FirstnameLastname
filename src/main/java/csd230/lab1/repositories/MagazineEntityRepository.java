package csd230.lab1.repositories;

import csd230.lab1.entities.MagazineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface MagazineEntityRepository extends JpaRepository<MagazineEntity, Long> {


    List<MagazineEntity> findByTitle(String title);

    List<MagazineEntity> findByTitleLike(String titlePattern);

    List<MagazineEntity> findByOrderQtyGreaterThan(int orderQty);

    List<MagazineEntity> findByOrderQtyLessThan(int orderQty);

    List<MagazineEntity> findByCurrentIssueAfter(LocalDateTime date);

    List<MagazineEntity> findByCurrentIssueBefore(LocalDateTime date);


    @Query("SELECT m FROM MagazineEntity m WHERE m.price < :maxPrice AND m.orderQty > :minOrderQty")
    List<MagazineEntity> findByPriceLessThanAndOrderQtyGreaterThan(
            @Param("maxPrice") double maxPrice,
            @Param("minOrderQty") int minOrderQty);
}