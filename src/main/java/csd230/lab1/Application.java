package csd230.lab1;

import com.github.javafaker.Faker;
import csd230.lab1.entities.*;
import csd230.lab1.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Application implements CommandLineRunner {
	private final ProductEntityRepository productRepository;
	private final BookEntityRepository bookRepository;
	private final MagazineEntityRepository magazineRepository;
	private final DiscMagEntityRepository discMagRepository;
	private final TicketEntityRepository ticketRepository;
	private final CartEntityRepository cartRepository;
	private final LipstickEntityRepository lipstickRepository;
	private final FoundationEntityRepository foundationRepository;

	public Application(ProductEntityRepository productRepository,
					   BookEntityRepository bookRepository,
					   MagazineEntityRepository magazineRepository,
					   DiscMagEntityRepository discMagRepository,
					   TicketEntityRepository ticketRepository,
					   CartEntityRepository cartRepository,
					   LipstickEntityRepository lipstickRepository,
					   FoundationEntityRepository foundationRepository) {
		this.productRepository = productRepository;
		this.bookRepository = bookRepository;
		this.magazineRepository = magazineRepository;
		this.discMagRepository = discMagRepository;
		this.ticketRepository = ticketRepository;
		this.cartRepository = cartRepository;
		this.lipstickRepository = lipstickRepository;
		this.foundationRepository = foundationRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}



	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Faker faker = new Faker();

			// ==================== NEW: MANY-TO-MANY RELATIONSHIP DEMONSTRATION ====================
			System.out.println("=".repeat(60));
			System.out.println("DEMONSTRATING MANY-TO-MANY RELATIONSHIP");
			System.out.println("=".repeat(60));


			CartEntity manyToManyCart = new CartEntity();
			cartRepository.save(manyToManyCart);
			System.out.println("\n[1] Created Cart for Many-to-Many demo (ID: " + manyToManyCart.getId() + ")");


			BookEntity demoBook = new BookEntity(
					"Spring Data JPA Guide",
					39.99,
					25,
					"John Expert",
					"978-1234567890"
			);
			bookRepository.save(demoBook);

			TicketEntity demoTicket = new TicketEntity(
					"Java Conference 2024",
					299.99
			);
			ticketRepository.save(demoTicket);

			LipstickEntity demoLipstick = new LipstickEntity(
					"Normal",
					"Matte",
					24.99
			);
			lipstickRepository.save(demoLipstick);

			System.out.println("\n[2] Created 3 products for Many-to-Many demo:");
			System.out.println("    • Book: " + demoBook.getTitle() + " (ID: " + demoBook.getId() + ")");
			System.out.println("    • Ticket: " + demoTicket.getDescription() + " (ID: " + demoTicket.getId() + ")");
			System.out.println("    • Lipstick: " + demoLipstick.getFinishType() + " finish (ID: " + demoLipstick.getId() + ")");


			manyToManyCart.addProduct(demoBook);
			manyToManyCart.addProduct(demoTicket);
			manyToManyCart.addProduct(demoLipstick);
			cartRepository.save(manyToManyCart);

			System.out.println("\n[3] Added all 3 products to Cart ID " + manyToManyCart.getId());


			CartEntity retrievedCart = cartRepository.findById(manyToManyCart.getId()).orElseThrow();
			System.out.println("\n[4] Retrieved Cart ID " + retrievedCart.getId() + " contains " +
					retrievedCart.getProducts().size() + " products:");

			retrievedCart.getProducts().forEach(product -> {
				System.out.println("    • " + product.getClass().getSimpleName() +
						" ID: " + product.getId());
			});


			System.out.println("\n[5] Bidirectional relationship verification:");
			System.out.println("    Cart ID " + retrievedCart.getId() + " has " +
					retrievedCart.getProducts().size() + " products");

			demoBook.getCarts().forEach(cart -> {
				System.out.println("    Book ID " + demoBook.getId() + " is in Cart ID " + cart.getId());
			});

			// ==================== NEW: NICHE ITEMS SAVING AND SELLING ====================
			System.out.println("\n" + "=".repeat(60));
			System.out.println("DEMONSTRATING NICHE ITEMS SAVING AND SELLING");
			System.out.println("=".repeat(60));


			FoundationEntity nicheFoundation = new FoundationEntity(
					"Oily",
					"Full Coverage",
					34.99
			);
			foundationRepository.save(nicheFoundation);

			LipstickEntity nicheLipstick1 = new LipstickEntity(
					"Dry",
					"Glossy",
					22.50
			);
			lipstickRepository.save(nicheLipstick1);

			LipstickEntity nicheLipstick2 = new LipstickEntity(
					"Combination",
					"Satin",
					26.99
			);
			lipstickRepository.save(nicheLipstick2);

			System.out.println("\n[1] Saved 3 niche cosmetics:");
			System.out.println("    • Foundation for " + nicheFoundation.getSkinType() +
					" skin, " + nicheFoundation.getCoverageLevel() +
					" coverage (ID: " + nicheFoundation.getId() + ")");
			System.out.println("    • Lipstick for " + nicheLipstick1.getSkinType() +
					" skin, " + nicheLipstick1.getFinishType() +
					" finish (ID: " + nicheLipstick1.getId() + ")");
			System.out.println("    • Lipstick for " + nicheLipstick2.getSkinType() +
					" skin, " + nicheLipstick2.getFinishType() +
					" finish (ID: " + nicheLipstick2.getId() + ")");


			CartEntity cosmeticsCart = new CartEntity();
			cosmeticsCart.addProduct(nicheFoundation);
			cosmeticsCart.addProduct(nicheLipstick1);
			cosmeticsCart.addProduct(nicheLipstick2);
			cartRepository.save(cosmeticsCart);

			System.out.println("\n[2] Created Cosmetics Cart with 3 niche items (Cart ID: " +
					cosmeticsCart.getId() + ")");


			System.out.println("\n[3] SELLING NICHE COSMETICS:");
			System.out.println("-".repeat(40));

			nicheFoundation.sellItem();
			nicheLipstick1.sellItem();
			nicheLipstick2.sellItem();

			System.out.println("\n[4] Summary of sold niche items:");
			System.out.println("    • Foundation: ID " + nicheFoundation.getId() +
					" for " + nicheFoundation.getSkinType() + " skin");
			System.out.println("    • Lipstick 1: ID " + nicheLipstick1.getId() +
					" with " + nicheLipstick1.getFinishType() + " finish");
			System.out.println("    • Lipstick 2: ID " + nicheLipstick2.getId() +
					" with " + nicheLipstick2.getFinishType() + " finish");
		System.out.println("=== STARTING COMPLETE CRUD DEMONSTRATION ===\n");


		System.out.println("Clearing database...");
		cartRepository.deleteAll();
		productRepository.deleteAll();
		System.out.println("Database cleared.\n");

		// ==================== CREATE OPERATIONS ====================
		System.out.println("=== CREATE OPERATIONS FOR ALL ENTITIES ===");

		BookEntity book1 = new BookEntity(
				faker.book().title(),
				Double.parseDouble(faker.commerce().price(10.0, 50.0)),
				faker.number().numberBetween(1, 100),
				faker.book().author()
		);
		book1.setIsbn(faker.code().isbn10());
		bookRepository.save(book1);
		System.out.println("Created Book 1: " + book1);

		BookEntity book2 = new BookEntity(
				faker.book().title(),
				Double.parseDouble(faker.commerce().price(10.0, 50.0)),
				faker.number().numberBetween(1, 100),
				faker.book().author()
		);
		book2.setIsbn(faker.code().isbn10());
		bookRepository.save(book2);
		System.out.println("Created Book 2: " + book2);


		MagazineEntity magazine1 = new MagazineEntity(
				faker.lorem().word() + " Magazine",
				12.99,
				faker.number().numberBetween(10, 50),
				100,
				LocalDateTime.now()
		);
		magazineRepository.save(magazine1);
		System.out.println("Created Magazine 1: " + magazine1);

		MagazineEntity magazine2 = new MagazineEntity(
				faker.lorem().word() + " Weekly",
				9.99,
				faker.number().numberBetween(10, 50),
				75,
				LocalDateTime.now().plusDays(14)
		);
		magazineRepository.save(magazine2);
		System.out.println("Created Magazine 2: " + magazine2);


		DiscMagEntity discMag1 = new DiscMagEntity(
				"Tech Disc Magazine",
				15.99,
				faker.number().numberBetween(10, 30),
				75,
				LocalDateTime.now().plusDays(7),
				true
		);
		discMagRepository.save(discMag1);
		System.out.println("Created DiscMag: " + discMag1);

		TicketEntity ticket1 = new TicketEntity(
				"Concert: " + faker.artist().name(),
				Double.parseDouble(faker.commerce().price(50.0, 200.0))
		);
		ticketRepository.save(ticket1);
		System.out.println("Created Ticket 1: " + ticket1);

		TicketEntity ticket2 = new TicketEntity(
				"Sports: " + faker.team().sport() + " Game",
				Double.parseDouble(faker.commerce().price(30.0, 150.0))
		);
		ticketRepository.save(ticket2);
		System.out.println("Created Ticket 2: " + ticket2);

		LipstickEntity lipstick1 = new LipstickEntity(
				"Combination",
				"Matte",
				24.99
		);
		lipstickRepository.save(lipstick1);
		System.out.println("Created Lipstick 1: " + lipstick1);

		LipstickEntity lipstick2 = new LipstickEntity(
				"Dry",
				"Glossy",
				19.99
		);
		lipstickRepository.save(lipstick2);
		System.out.println("Created Lipstick 2: " + lipstick2);


		FoundationEntity foundation1 = new FoundationEntity(
				"Oily",
				"Medium Coverage",
				32.50
		);
		foundationRepository.save(foundation1);
		System.out.println("Created Foundation 1: " + foundation1);

		FoundationEntity foundation2 = new FoundationEntity(
				"Normal",
				"Light Coverage",
				28.75
		);
		foundationRepository.save(foundation2);
		System.out.println("Created Foundation 2: " + foundation2);


		CartEntity cart1 = new CartEntity();
		cartRepository.save(cart1);
		System.out.println("Created Cart 1: " + cart1);

		CartEntity cart2 = new CartEntity();
		cartRepository.save(cart2);
		System.out.println("Created Cart 2: " + cart2);

		CartEntity cart3 = new CartEntity();
		cartRepository.save(cart3);
		System.out.println("Created Cart 3: " + cart3);


		System.out.println("\nAdding products to carts...");


		cart1.addProduct(book1);
		cart1.addProduct(lipstick1);
		cart1.addProduct(ticket1);
		cartRepository.save(cart1);
		System.out.println("Added 3 mixed products to Cart 1");

		cart2.addProduct(book2);
		cart2.addProduct(magazine1);
		cart2.addProduct(discMag1);
		cartRepository.save(cart2);
		System.out.println("Added 3 publication products to Cart 2");


		cart3.addProduct(lipstick2);
		cart3.addProduct(foundation1);
		cart3.addProduct(foundation2);
		cartRepository.save(cart3);
		System.out.println("Added 3 cosmetics to Cart 3");

		// ==================== READ OPERATIONS ====================
		System.out.println("\n=== READ OPERATIONS FOR ALL ENTITIES ===");

		System.out.println("\n--- Books ---");
		List<BookEntity> allBooks = bookRepository.findAll();
		System.out.println("All Books (" + allBooks.size() + "):");
		allBooks.forEach(b -> System.out.println("  " + b));

		System.out.println("\n--- Magazines ---");
		List<MagazineEntity> allMagazines = magazineRepository.findAll();
		System.out.println("All Magazines (" + allMagazines.size() + "):");
		allMagazines.forEach(m -> System.out.println("  " + m));

		System.out.println("\n--- DiscMags ---");
		List<DiscMagEntity> allDiscMags = discMagRepository.findAll();
		System.out.println("All DiscMags (" + allDiscMags.size() + "):");
		allDiscMags.forEach(d -> System.out.println("  " + d));

		System.out.println("\n--- Tickets ---");
		List<TicketEntity> allTickets = ticketRepository.findAll();
		System.out.println("All Tickets (" + allTickets.size() + "):");
		allTickets.forEach(t -> System.out.println("  " + t));

		System.out.println("\n--- Lipsticks ---");
		List<LipstickEntity> allLipsticks = lipstickRepository.findAll();
		System.out.println("All Lipsticks (" + allLipsticks.size() + "):");
		allLipsticks.forEach(l -> System.out.println("  " + l));

		System.out.println("\n--- Foundations ---");
		List<FoundationEntity> allFoundations = foundationRepository.findAll();
		System.out.println("All Foundations (" + allFoundations.size() + "):");
		allFoundations.forEach(f -> System.out.println("  " + f));

		System.out.println("\n--- Carts ---");
		List<CartEntity> allCarts = cartRepository.findAll();
		System.out.println("All Carts (" + allCarts.size() + "):");
		allCarts.forEach(c -> System.out.println("  " + c + " with " + c.getProducts().size() + " products"));

		System.out.println("\n--- All Products ---");
		List<ProductEntity> allProducts = productRepository.findAll();
		System.out.println("Total Products (" + allProducts.size() + "):");
		allProducts.forEach(p -> System.out.println("  " + p.getClass().getSimpleName() + " - " + p));

		// ==================== DERIVED QUERIES ====================
		System.out.println("\n=== DERIVED QUERIES DEMONSTRATION ===");


		List<BookEntity> booksByPriceRange = bookRepository.findByPriceRange(15.0, 40.0);
		System.out.println("Books in price range $15-$40: " + booksByPriceRange.size());


		List<MagazineEntity> highOrderMags = magazineRepository.findByOrderQtyGreaterThan(50);
		System.out.println("Magazines with order qty > 50: " + highOrderMags.size());


		List<TicketEntity> expensiveTickets = ticketRepository.findByPriceGreaterThan(100.0);
		System.out.println("Tickets over $100: " + expensiveTickets.size());


		List<LipstickEntity> matteLipsticks = lipstickRepository.findByFinishType("Matte");
		System.out.println("Matte lipsticks: " + matteLipsticks.size());

		List<FoundationEntity> oilyFoundations = foundationRepository.findBySkinType("Oily");
		System.out.println("Foundations for oily skin: " + oilyFoundations.size());


		List<CartEntity> fullCarts = cartRepository.findByProductCountGreaterThan(2);
		System.out.println("Carts with more than 2 products: " + fullCarts.size());

		// ==================== UPDATE OPERATIONS ====================
		System.out.println("\n=== UPDATE OPERATIONS FOR ALL ENTITIES ===");


		Optional<BookEntity> bookToUpdate = bookRepository.findById(book1.getId());
		if (bookToUpdate.isPresent()) {
			BookEntity book = bookToUpdate.get();
			System.out.println("\n--- Updating Book ---");
			System.out.println("Before update: " + book);
			book.setPrice(book.getPrice() + 5.0);
			book.setCopies(book.getCopies() + 10);
			book.setAuthor(faker.book().author());
			bookRepository.save(book);
			System.out.println("After update: " + book);
		}


		Optional<MagazineEntity> magToUpdate = magazineRepository.findById(magazine1.getId());
		if (magToUpdate.isPresent()) {
			MagazineEntity magazine = magToUpdate.get();
			System.out.println("\n--- Updating Magazine ---");
			System.out.println("Before update: " + magazine);
			magazine.setOrderQty(magazine.getOrderQty() + 25);
			magazine.setCurrentIssue(LocalDateTime.now().plusMonths(1));
			magazine.setTitle("Updated " + magazine.getTitle());
			magazineRepository.save(magazine);
			System.out.println("After update: " + magazine);
		}


		Optional<TicketEntity> ticketToUpdate = ticketRepository.findById(ticket1.getId());
		if (ticketToUpdate.isPresent()) {
			TicketEntity ticket = ticketToUpdate.get();
			System.out.println("\n--- Updating Ticket ---");
			System.out.println("Before update: " + ticket);
			ticket.setPrice(ticket.getPrice() * 1.1); // 10% price increase
			ticket.setDescription("VIP " + ticket.getDescription());
			ticketRepository.save(ticket);
			System.out.println("After update: " + ticket);
		}


		Optional<LipstickEntity> lipstickToUpdate = lipstickRepository.findById(lipstick1.getId());
		if (lipstickToUpdate.isPresent()) {
			LipstickEntity lipstick = lipstickToUpdate.get();
			System.out.println("\n--- Updating Lipstick ---");
			System.out.println("Before update: " + lipstick);
			lipstick.setPrice(lipstick.getPrice() + 3.0);
			lipstick.setFinishType("Satin");
			lipstick.setSkinType("Normal");
			lipstickRepository.save(lipstick);
			System.out.println("After update: " + lipstick);
		}


		Optional<FoundationEntity> foundationToUpdate = foundationRepository.findById(foundation1.getId());
		if (foundationToUpdate.isPresent()) {
			FoundationEntity foundation = foundationToUpdate.get();
			System.out.println("\n--- Updating Foundation ---");
			System.out.println("Before update: " + foundation);
			foundation.setPrice(foundation.getPrice() - 5.0);
			foundation.setCoverageLevel("Full Coverage");
			foundation.setSkinType("Combination");
			foundationRepository.save(foundation);
			System.out.println("After update: " + foundation);
		}





		// ==================== DELETE OPERATIONS ====================
		System.out.println("\n=== DELETE OPERATIONS ===");

		System.out.println("\n--- Counts Before Deletion ---");
		try {
			System.out.println("Total Products: " + productRepository.count());
			System.out.println("Books: " + bookRepository.count());
			System.out.println("Magazines: " + magazineRepository.count());
			System.out.println("DiscMags: " + discMagRepository.count());
			System.out.println("Tickets: " + ticketRepository.count());
			System.out.println("Lipsticks: " + lipstickRepository.count());
			System.out.println("Foundations: " + foundationRepository.count());
			System.out.println("Carts: " + cartRepository.count());
		} catch (Exception e) {
			System.out.println("Could not get counts: " + e.getMessage());
		}

		System.out.println("\n--- Performing Deletions ---");

		if (cart3.getId() != null) {
			try {
				Optional<CartEntity> cart3Opt = cartRepository.findById(cart3.getId());
				if (cart3Opt.isPresent()) {
					CartEntity cart3ToDelete = cart3Opt.get();
					cart3ToDelete.getProducts().clear();
					cartRepository.save(cart3ToDelete);
					cartRepository.deleteById(cart3.getId());
					System.out.println("Deleted Cart 3 with ID: " + cart3.getId());
				}
			} catch (Exception e) {
				System.out.println("Error deleting Cart 3: " + e.getMessage());
			}
		}

		try {
			if (book2.getId() != null) {
				bookRepository.deleteById(book2.getId());
				System.out.println("Deleted Book 2 with ID: " + book2.getId());
			}
		} catch (Exception e) {
			System.out.println("Error deleting Book 2: " + e.getMessage());
		}

		try {
			if (magazine2.getId() != null) {
				magazineRepository.deleteById(magazine2.getId());
				System.out.println("Deleted Magazine 2 with ID: " + magazine2.getId());
			}
		} catch (Exception e) {
			System.out.println("Error deleting Magazine 2: " + e.getMessage());
		}

		try {
			if (ticket2.getId() != null) {
				ticketRepository.deleteById(ticket2.getId());
				System.out.println("Deleted Ticket 2 with ID: " + ticket2.getId());
			}
		} catch (Exception e) {
			System.out.println("Error deleting Ticket 2: " + e.getMessage());
		}

		try {
			if (lipstick2.getId() != null) {
				lipstickRepository.deleteById(lipstick2.getId());
				System.out.println("Deleted Lipstick 2 with ID: " + lipstick2.getId());
			}
		} catch (Exception e) {
			System.out.println("Error deleting Lipstick 2: " + e.getMessage());
		}

		System.out.println("\n--- Counts After Deletion ---");
		try {
			System.out.println("Total Products: " + productRepository.count());
		} catch (Exception e) {
			System.out.println("Could not count total products: " + e.getMessage());
		}
		try {
			System.out.println("Books: " + bookRepository.count());
		} catch (Exception e) {
			System.out.println("Could not count books: " + e.getMessage());
		}
		try {
			System.out.println("Magazines: " + magazineRepository.count());
		} catch (Exception e) {
			System.out.println("Could not count magazines: " + e.getMessage());
		}
		try {
			System.out.println("DiscMags: " + discMagRepository.count());
		} catch (Exception e) {
			System.out.println("Could not count disc mags: " + e.getMessage());
		}
		try {
			System.out.println("Tickets: " + ticketRepository.count());
		} catch (Exception e) {
			System.out.println("Could not count tickets: " + e.getMessage());
		}
		try {
			System.out.println("Lipsticks: " + lipstickRepository.count());
		} catch (Exception e) {
			System.out.println("Could not count lipsticks: " + e.getMessage());
		}
		try {
			System.out.println("Foundations: " + foundationRepository.count());
		} catch (Exception e) {
			System.out.println("Could not count foundations: " + e.getMessage());
		}
		try {
			System.out.println("Carts: " + cartRepository.count());
		} catch (Exception e) {
			System.out.println("Could not count carts: " + e.getMessage());
		}

		// ==================== FINAL SUMMARY ====================
		System.out.println("\n" + "=".repeat(60));
		System.out.println("DEMONSTRATION SUMMARY");
		System.out.println("=".repeat(60));


		System.out.println("\nAll Carts in Database:");
		cartRepository.findAll().forEach(cart -> {
			System.out.println("  Cart ID " + cart.getId() + ": " +
					cart.getProducts().size() + " products");
			if (cart.getId().equals(manyToManyCart.getId())) {
				System.out.println("    [Many-to-Many Demo Cart]");
			}
			if (cart.getId().equals(cosmeticsCart.getId())) {
				System.out.println("    [Niche Cosmetics Demo Cart]");
			}
		});


		System.out.println("\nNiche Cosmetics Count:");
		System.out.println("  Foundations: " + foundationRepository.count());
		System.out.println("  Lipsticks: " + lipstickRepository.count());

		System.out.println("\n" + "=".repeat(60));
		System.out.println("ALL DEMONSTRATIONS COMPLETE");
		System.out.println("=".repeat(60));
	}
}