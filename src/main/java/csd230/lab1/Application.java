package csd230.lab1;

import csd230.lab1.entities.BookEntity;
import csd230.lab1.entities.CartEntity;
import csd230.lab1.repositories.BookRepository;
import csd230.lab1.repositories.CartRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final CartRepository cartRepository;

    public Application(BookRepository bookRepository, CartRepository cartRepository) {
        this.bookRepository = bookRepository;
        this.cartRepository = cartRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) {

        // Ensure default cart exists first (first insert should be id=1 in a new DB)
        if (cartRepository.count() == 0) {
            cartRepository.save(new CartEntity());
        }

        // Seed books only if none exist
        if (bookRepository.count() == 0) {
            bookRepository.save(new BookEntity("The Hobbit", 19.99, 10, "J.R.R. Tolkien", "ISBN-001"));
            bookRepository.save(new BookEntity("Harry Potter", 24.99, 7, "J.K. Rowling", "ISBN-002"));
            bookRepository.save(new BookEntity("Clean Code", 39.99, 5, "Robert C. Martin", "ISBN-003"));
        }
    }
}
