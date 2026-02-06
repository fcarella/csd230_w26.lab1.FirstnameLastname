package csd230.lab1.repositories;

import csd230.lab1.entities.LaptopEntity;
import csd230.lab1.entities.PhoneEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ElectronicsRepositoryTest {

    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private CartRepository cartRepository;

    @Test
    void laptopAndPhoneCrud_works() {

        // ✅ IMPORTANT: clear carts FIRST to avoid FK issues
        cartRepository.deleteAll();
        laptopRepository.deleteAll();
        phoneRepository.deleteAll();

        LaptopEntity laptop = new LaptopEntity(
                "Moore, Beier and Kutch",
                999.99,
                16
        );

        PhoneEntity phone = new PhoneEntity(
                "Boyer LLC",
                799.99,
                true
        );

        laptopRepository.save(laptop);
        phoneRepository.save(phone);

        assertThat(laptopRepository.findAll()).hasSize(1);
        assertThat(phoneRepository.findAll()).hasSize(1);

        assertThat(laptopRepository.findAll().get(0).getBrand())
                .isEqualTo("Moore, Beier and Kutch");

        assertThat(phoneRepository.findAll().get(0).isHas5g())
                .isTrue();
    }
}
