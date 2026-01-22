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
	private final BookEntityRepository bookRepository;
	private final CartEntityRepository cartRepository;

	public Application(BookEntityRepository bookRepository,
					   CartEntityRepository cartRepository) {
		this.bookRepository = bookRepository;
		this.cartRepository = cartRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Faker faker = new Faker();

		System.out.println("=== CREATING FAKE BOOKS ===");

		// Clear existing data first to avoid conflicts
		cartRepository.deleteAll();
		bookRepository.deleteAll();

		// Create and save 5 fake books
		for (int i = 1; i <= 5; i++) {
			BookEntity book = new BookEntity(
					faker.book().title(),
					Double.parseDouble(faker.commerce().price(10.0, 50.0)),
					faker.number().numberBetween(1, 100),
					faker.book().author()
			);
			book.setIsbn(faker.code().isbn10());
			bookRepository.save(book);
			System.out.println("Created Book " + i + ": " + book.getTitle() +
					" by " + book.getAuthor() +
					" - $" + book.getPrice());
		}

		boolean cartExists = cartRepository.existsById(1L);

		if (cartExists) {
			System.out.println("Cart with ID 1 already exists. Skipping creation...");
		} else {

			CartEntity cart = new CartEntity();
			cart = cartRepository.save(cart);
			System.out.println("\nCreated Cart with ID: " + cart.getId());
		}

		System.out.println("\n=== DATABASE INITIALIZED ===");
		System.out.println("Total Books: " + bookRepository.count());
		System.out.println("Total Carts: " + cartRepository.count());

		// Show all books
		List<BookEntity> allBooks = bookRepository.findAll();
		System.out.println("\nAvailable Books:");
		allBooks.forEach(b -> System.out.println("  ID: " + b.getId() +
				" - " + b.getTitle() +
				" ($" + b.getPrice() + ")"));
	}
}