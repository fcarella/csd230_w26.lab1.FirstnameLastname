package csd230.lab1.repositories;

import csd230.lab1.entities.LipstickEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface LipstickEntityRepository extends JpaRepository<LipstickEntity, Long> {

    List<LipstickEntity> findBySkinType(String skinType);

    List<LipstickEntity> findByFinishType(String finishType);

    List<LipstickEntity> findBySkinTypeLike(String skinTypePattern);

    List<LipstickEntity> findByFinishTypeLike(String finishTypePattern);

    List<LipstickEntity> findByPriceGreaterThan(double price);

    List<LipstickEntity> findByPriceLessThan(double price);

    @Query("SELECT l FROM LipstickEntity l WHERE l.skinType = :skinType AND l.finishType = :finishType")
    List<LipstickEntity> findBySkinTypeAndFinishType(
            @Param("skinType") String skinType,
            @Param("finishType") String finishType);

    @Query("SELECT l FROM LipstickEntity l WHERE l.price BETWEEN :minPrice AND :maxPrice AND l.skinType LIKE %:skinType%")
    List<LipstickEntity> findByPriceRangeAndSkinTypeContaining(
            @Param("minPrice") double minPrice,
            @Param("maxPrice") double maxPrice,
            @Param("skinType") String skinType);
}