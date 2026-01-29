package csd230.lab1.repositories;

import csd230.lab1.entities.MagazineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MagazineEntityRepository extends JpaRepository<MagazineEntity, Long> {

    // Derived query example (optional but recommended for the lab)
    List<MagazineEntity> findByTitle(String title);

    // LIKE / contains search
    List<MagazineEntity> findByTitleContainingIgnoreCase(String titlePart);
}
