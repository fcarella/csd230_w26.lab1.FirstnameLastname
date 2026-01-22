package csd230.lab1;

import csd230.lab1.entities.*;
import csd230.lab1.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ProductRepositoryTest {

    // Correct field names matching your repository interfaces:
    @Autowired
    private BookEntityRepository bookEntityRepository;  // NOT bookRepository

    @Autowired
    private MagazineEntityRepository magazineEntityRepository;

    @Autowired
    private DiscMagEntityRepository discMagEntityRepository;

    @Autowired
    private TicketEntityRepository ticketEntityRepository;

    @Autowired
    private FoundationEntityRepository foundationEntityRepository;

    @Autowired
    private LipstickEntityRepository lipstickEntityRepository;

    @Autowired
    private CartEntityRepository cartEntityRepository;

    @Autowired
    private ProductEntityRepository productEntityRepository;

    @Test
    void testBookEntityCRUD() {
        BookEntity book = new BookEntity("Clean Code", 45.99, 10, "Robert C. Martin", "978-0132350884");
        BookEntity savedBook = bookEntityRepository.save(book);  // Use bookEntityRepository

        assertNotNull(savedBook.getId());

        // READ
        Optional<BookEntity> found = bookEntityRepository.findById(savedBook.getId());
        assertTrue(found.isPresent());
        assertEquals("Clean Code", found.get().getTitle());

        // UPDATE
        found.get().setPrice(49.99);
        bookEntityRepository.save(found.get());
        Optional<BookEntity> updated = bookEntityRepository.findById(savedBook.getId());
        assertEquals(49.99, updated.get().getPrice());

        // DELETE
        bookEntityRepository.deleteById(savedBook.getId());
        assertFalse(bookEntityRepository.findById(savedBook.getId()).isPresent());
    }

    @Test
    void testMagazineEntityCRUD() {
        MagazineEntity mag = new MagazineEntity("Tech Monthly", 9.99, 50, 100, LocalDateTime.now());
        MagazineEntity saved = magazineEntityRepository.save(mag);  // Use magazineEntityRepository

        assertNotNull(saved.getId());

        Optional<MagazineEntity> found = magazineEntityRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("Tech Monthly", found.get().getTitle());

        magazineEntityRepository.deleteById(saved.getId());
        assertFalse(magazineEntityRepository.findById(saved.getId()).isPresent());
    }

    @Test
    void testDiscMagEntityCRUD() {
        DiscMagEntity discMag = new DiscMagEntity("PC Gamer", 12.99, 30, 75, LocalDateTime.now(), true);
        DiscMagEntity saved = discMagEntityRepository.save(discMag);  // Use discMagEntityRepository

        assertNotNull(saved.getId());
        assertTrue(saved.isHasDisc());

        discMagEntityRepository.deleteById(saved.getId());
        assertFalse(discMagEntityRepository.findById(saved.getId()).isPresent());
    }

    @Test
    void testTicketEntityCRUD() {
        TicketEntity ticket = new TicketEntity("Concert Ticket", 89.50);
        TicketEntity saved = ticketEntityRepository.save(ticket);  // Use ticketEntityRepository

        assertNotNull(saved.getId());
        assertEquals("Concert Ticket", saved.getDescription());

        ticketEntityRepository.deleteById(saved.getId());
        assertFalse(ticketEntityRepository.findById(saved.getId()).isPresent());
    }

    @Test
    void testFoundationEntityCRUD() {
        FoundationEntity foundation = new FoundationEntity("Oily", "Full Coverage", 34.99);
        FoundationEntity saved = foundationEntityRepository.save(foundation);  // Use foundationEntityRepository

        assertNotNull(saved.getId());
        assertEquals("Oily", saved.getSkinType());
        assertEquals("Full Coverage", saved.getCoverageLevel());

        foundationEntityRepository.deleteById(saved.getId());
        assertFalse(foundationEntityRepository.findById(saved.getId()).isPresent());
    }

    @Test
    void testLipstickEntityCRUD() {
        LipstickEntity lipstick = new LipstickEntity("Dry", "Matte", 22.50);
        LipstickEntity saved = lipstickEntityRepository.save(lipstick);  // Use lipstickEntityRepository

        assertNotNull(saved.getId());
        assertEquals("Matte", saved.getFinishType());

        lipstickEntityRepository.deleteById(saved.getId());
        assertFalse(lipstickEntityRepository.findById(saved.getId()).isPresent());
    }

    @Test
    void testCartEntityCRUD() {
        CartEntity cart = new CartEntity();
        CartEntity savedCart = cartEntityRepository.save(cart);  // Use cartEntityRepository

        assertNotNull(savedCart.getId());

        BookEntity book = new BookEntity("Java Basics", 29.99, 5, "John Doe", "123-456789");
        bookEntityRepository.save(book);

        savedCart.addProduct(book);
        cartEntityRepository.save(savedCart);


        cartEntityRepository.deleteById(savedCart.getId());
        assertFalse(cartEntityRepository.findById(savedCart.getId()).isPresent());
    }

    // Test custom queries
    @Test
    void testCustomQueries() {
        // Test BookEntity custom query
        BookEntity book = new BookEntity("Spring in Action", 59.99, 20, "Craig Walls", "978-1617294945");
        bookEntityRepository.save(book);

        List<BookEntity> booksByTitle = bookEntityRepository.findByTitle("Spring in Action");
        assertFalse(booksByTitle.isEmpty());
        assertEquals("Craig Walls", booksByTitle.get(0).getAuthor());

        // Test TicketEntity custom query
        TicketEntity ticket = new TicketEntity("Music Festival VIP", 199.99);
        ticketEntityRepository.save(ticket);

        List<TicketEntity> tickets = ticketEntityRepository.findByDescriptionContainingIgnoreCase("festival");
        assertFalse(tickets.isEmpty());
    }
}