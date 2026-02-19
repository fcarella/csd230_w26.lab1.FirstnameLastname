package csd230.lab1.repositories;

import csd230.lab1.entities.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MiscEntitiesCrudTest {

    @Autowired private MagazineEntityRepository magazineRepo;
    @Autowired private DiscMagEntityRepository discMagRepo;
    @Autowired private TicketEntityRepository ticketRepo;
    @Autowired private LaptopEntityRepository laptopRepo;
    @Autowired private PhoneEntityRepository phoneRepo;

    @Test
    void magazine_crud() {
        MagazineEntity m = new MagazineEntity("Tech Monthly", 9.99, 20, 50, LocalDateTime.now());
        m.setDescription("mag desc");

        MagazineEntity saved = magazineRepo.save(m);
        assertThat(magazineRepo.findById(saved.getId())).isPresent();

        saved.setTitle("Tech Monthly Updated");
        magazineRepo.save(saved);
        assertThat(magazineRepo.findById(saved.getId()).orElseThrow().getTitle())
                .isEqualTo("Tech Monthly Updated");

        magazineRepo.delete(saved);
        assertThat(magazineRepo.findById(saved.getId())).isEmpty();
    }

    @Test
    void discmag_crud() {
        DiscMagEntity dm = new DiscMagEntity("Gaming Special", 14.99, 10, 20, LocalDateTime.now(), true);
        dm.setDescription("disc mag");

        DiscMagEntity saved = discMagRepo.save(dm);
        assertThat(discMagRepo.findById(saved.getId())).isPresent();

        discMagRepo.delete(saved);
        assertThat(discMagRepo.findById(saved.getId())).isEmpty();
    }

    @Test
    void ticket_crud() {
        // Adjust construction to match YOUR TicketEntity (use setters if needed)
        TicketEntity t = new TicketEntity();
        t.setTitle("Concert Ticket");
        t.setPrice(59.99);
        t.setDescription("General admission");

        TicketEntity saved = ticketRepo.save(t);
        assertThat(ticketRepo.findById(saved.getId())).isPresent();

        saved.setPrice(49.99);
        ticketRepo.save(saved);
        assertThat(ticketRepo.findById(saved.getId()).orElseThrow().getPrice()).isEqualTo(49.99);

        ticketRepo.delete(saved);
        assertThat(ticketRepo.findById(saved.getId())).isEmpty();
    }

    @Test
    void electronics_crud() {
        LaptopEntity laptop = new LaptopEntity("ThinkPad", 999.99, 24, 16);
        laptop.setDescription("business laptop");

        PhoneEntity phone = new PhoneEntity("Galaxy", 799.99, 12, true);
        phone.setDescription("flagship");

        LaptopEntity savedLaptop = laptopRepo.save(laptop);
        PhoneEntity savedPhone = phoneRepo.save(phone);

        assertThat(laptopRepo.findById(savedLaptop.getId())).isPresent();
        assertThat(phoneRepo.findById(savedPhone.getId())).isPresent();

        savedLaptop.setRamGB(32);
        laptopRepo.save(savedLaptop);
        assertThat(laptopRepo.findById(savedLaptop.getId()).orElseThrow().getRamGB()).isEqualTo(32);

        phoneRepo.delete(savedPhone);
        assertThat(phoneRepo.findById(savedPhone.getId())).isEmpty();
    }
}
