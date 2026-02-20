package csd230.lab1;

import csd230.lab1.entities.BookEntity;
import csd230.lab1.entities.UserEntity;
import csd230.lab1.repositories.BookRepository;
import csd230.lab1.repositories.UserEntityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final UserEntityRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Application(BookRepository bookRepository,
                       UserEntityRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) {

        // Seed books only if none exist
        if (bookRepository.count() == 0) {
            bookRepository.save(new BookEntity("The Hobbit", 19.99, 10, "J.R.R. Tolkien", "ISBN-001"));
            bookRepository.save(new BookEntity("Harry Potter", 24.99, 7, "J.K. Rowling", "ISBN-002"));
            bookRepository.save(new BookEntity("Clean Code", 39.99, 5, "Robert C. Martin", "ISBN-003"));
        }

        // Seed default users if not exist
        if (userRepository.findByUsername("admin") == null) {
            userRepository.save(new UserEntity("admin", passwordEncoder.encode("admin"), "ADMIN"));
        }

        if (userRepository.findByUsername("user") == null) {
            userRepository.save(new UserEntity("user", passwordEncoder.encode("user"), "USER"));
        }
    }
}
