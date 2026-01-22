package csd230.lab1.repositories;

import csd230.lab1.entities.FoundationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface FoundationEntityRepository extends JpaRepository<FoundationEntity, Long> {

    List<FoundationEntity> findBySkinType(String skinType);

    List<FoundationEntity> findByCoverageLevel(String coverageLevel);

    List<FoundationEntity> findBySkinTypeLike(String skinTypePattern);

    List<FoundationEntity> findByCoverageLevelLike(String coverageLevelPattern);

    List<FoundationEntity> findByPriceGreaterThan(double price);

    List<FoundationEntity> findByPriceLessThan(double price);

    @Query("SELECT f FROM FoundationEntity f WHERE f.skinType = :skinType AND f.coverageLevel = :coverageLevel")
    List<FoundationEntity> findBySkinTypeAndCoverageLevel(
            @Param("skinType") String skinType,
            @Param("coverageLevel") String coverageLevel);

    @Query("SELECT f FROM FoundationEntity f WHERE f.price > :minPrice AND f.coverageLevel = :coverageLevel")
    List<FoundationEntity> findByPriceGreaterThanAndCoverageLevel(
            @Param("minPrice") double minPrice,
            @Param("coverageLevel") String coverageLevel);
}