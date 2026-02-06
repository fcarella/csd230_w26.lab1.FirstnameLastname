package csd230.lab1.repositories;

import csd230.lab1.entities.MagazineEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MagazineRepositoryTest {

    @Autowired
    private MagazineRepository magazineRepository;

    @Test
    void magazineCrud_works() {
        MagazineEntity mag = new MagazineEntity(
                "Test Mag",
                9.99,
                10,
                50,
                LocalDateTime.now()
        );

        mag = magazineRepository.save(mag);

        assertThat(mag.getId()).isNotNull();
        assertThat(magazineRepository.findById(mag.getId())).isPresent();
    }
}
