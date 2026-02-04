package csd230.lab1;

import com.github.javafaker.Faker;
import csd230.lab1.entities.*;
import csd230.lab1.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final LaptopEntityRepository laptopRepository;
    private final PhoneEntityRepository phoneRepository;

    private final BookEntityRepository bookRepo;
    private final MagazineEntityRepository magazineRepo;
    private final DiscMagEntityRepository discMagRepo;
    private final TicketEntityRepository ticketRepo;
    private final CartEntityRepository cartRepo;

    public Application(
            LaptopEntityRepository laptopRepository,
            PhoneEntityRepository phoneRepository,
            BookEntityRepository bookRepo,
            MagazineEntityRepository magazineRepo,
            DiscMagEntityRepository discMagRepo,
            TicketEntityRepository ticketRepo,
            CartEntityRepository cartRepo
    ) {
        this.laptopRepository = laptopRepository;
        this.phoneRepository = phoneRepository;
        this.bookRepo = bookRepo;
        this.magazineRepo = magazineRepo;
        this.discMagRepo = discMagRepo;
        this.ticketRepo = ticketRepo;
        this.cartRepo = cartRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) {
        Faker faker = new Faker();

        // ---------- CREATE ----------
        BookEntity b1 = new BookEntity(faker.book().title(), 19.99, 10, faker.book().author());
        b1.setIsbn("978-" + faker.number().digits(10));
        b1.setDescription(faker.lorem().sentence());

        BookEntity b2 = new BookEntity(faker.book().title(), 29.99, 5, faker.book().author());
        b2.setIsbn("978-" + faker.number().digits(10));
        b2.setDescription(faker.lorem().sentence());

        MagazineEntity m1 = new MagazineEntity("Tech Monthly", 9.99, 20, 50, java.time.LocalDateTime.now());
        m1.setDescription("Magazine about tech");

        DiscMagEntity dm1 = new DiscMagEntity("Gaming Special", 14.99, 10, 20, java.time.LocalDateTime.now(), true);
        dm1.setDescription("Magazine with bonus disc");

        TicketEntity t1 = new TicketEntity("Concert Ticket", 59.99, "General admission");
        TicketEntity t2 = new TicketEntity("Movie Ticket", 13.50, "Evening show");

        LaptopEntity laptop = new LaptopEntity("Lenovo ThinkPad", 999.99, 24, 16);
        laptop.setDescription("Business laptop");

        PhoneEntity phone = new PhoneEntity("Samsung Galaxy", 799.99, 12, true);
        phone.setDescription("Flagship phone");

        // Save products
        bookRepo.saveAll(List.of(b1, b2));
        magazineRepo.save(m1);
        discMagRepo.save(dm1);
        ticketRepo.saveAll(List.of(t1, t2));
        laptopRepository.save(laptop);
        phoneRepository.save(phone);

        // Create carts
        CartEntity c1 = new CartEntity();
        CartEntity c2 = new CartEntity();
        CartEntity c3 = new CartEntity();
        cartRepo.saveAll(List.of(c1, c2, c3));

        // Many-to-many: same product in multiple carts
        c1.addProduct(b1);
        c1.addProduct(m1);
        c1.addProduct(laptop);     // niche
        c1.addProduct(phone);      // niche

        c2.addProduct(b1);
        c2.addProduct(t1);

        c3.addProduct(m1);
        c3.addProduct(b2);
        c3.addProduct(dm1);

        cartRepo.saveAll(List.of(c1, c2, c3));

        // ---------- READ ----------
        System.out.println("=== ALL CARTS ===");
        for (CartEntity c : cartRepo.findAll()) {
            System.out.println("Cart " + c.getId());
            for (ProductEntity p : c.getProducts()) System.out.println("  " + p);
        }

        System.out.println("=== BOOK BY ISBN ===");
        System.out.println(bookRepo.findByIsbn(b1.getIsbn()));

        // ---------- UPDATE ----------
        b1.setTitle(b1.getTitle() + " (Updated)");
        bookRepo.save(b1);
        System.out.println("Updated book: " + bookRepo.findById(b1.getId()).orElseThrow());

        // ---------- DELETE ----------
        ticketRepo.delete(t2);
        System.out.println("Tickets after delete: " + ticketRepo.findAll().size());
    }
}
