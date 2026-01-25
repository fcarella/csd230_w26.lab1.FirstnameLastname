package csd230.lab1;

import csd230.lab1.entities.*;
import csd230.lab1.repositories.*;
import com.github.javafaker.Commerce;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AllEntitiesCRUDTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MagazineRepository magazineRepository;

    @Autowired
    private DiscMagRepository discMagRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private MotorcycleRepository motorcycleRepository;

    @Autowired
    private CartRepository cartRepository;

    private Faker getFaker() {
        return new Faker();
    }

    @Test
    @Order(1)
    void testProductCRUD() {
        Faker faker = getFaker();
        Commerce cm = faker.commerce();
        String name = cm.productName();
        String description = cm.material();
        double price = faker.number().randomDouble(2, 10, 100);

        BookEntity newProduct = new BookEntity();
        newProduct.setTitle(name);
        newProduct.setPrice(price);
        newProduct.setQuantity(10);
        newProduct.setAuthor(faker.book().author());

        BookEntity savedProduct = bookRepository.save(newProduct);

        Long savedProductID = savedProduct.getId();
        assertNotNull(savedProductID);

        ProductEntity retrievedProduct = productRepository.findById(savedProductID).orElseThrow();
        assertEquals(savedProductID, retrievedProduct.getId());
        assertEquals(name, retrievedProduct.getTitle());
        assertEquals(price, retrievedProduct.getPrice(), 0.01);
        assertEquals(10, retrievedProduct.getQuantity());

        // UPDATE
        retrievedProduct.setPrice(price + 10.0);
        retrievedProduct.setDescription("Updated: " + description);
        ProductEntity updatedProduct = productRepository.save(retrievedProduct);

        assertEquals(savedProductID, updatedProduct.getId());
        assertEquals(price + 10.0, updatedProduct.getPrice(), 0.01);
        assertTrue(updatedProduct.getDescription().startsWith("Updated: "));

        productRepository.delete(updatedProduct);
        Optional<ProductEntity> deletedProduct = productRepository.findById(savedProductID);
        assertFalse(deletedProduct.isPresent());
    }

    @Test
    @Order(2)
    void testBookEntityCRUD() {
        Faker faker = getFaker();

        BookEntity book = new BookEntity();
        book.setTitle(faker.book().title());
        book.setPrice(faker.number().randomDouble(2, 20, 50));
        book.setQuantity(faker.number().numberBetween(5, 50));
        book.setAuthor(faker.book().author());

        BookEntity savedBook = bookRepository.save(book);

        assertNotNull(savedBook.getId());
        Optional<BookEntity> foundBook = bookRepository.findById(savedBook.getId());
        assertTrue(foundBook.isPresent());
        assertEquals(book.getTitle(), foundBook.get().getTitle());
        assertEquals(book.getAuthor(), foundBook.get().getAuthor());

        savedBook.setPrice(39.99);
        savedBook.setAuthor("J.K. Rowling");
        savedBook.setQuantity(25);
        BookEntity updatedBook = bookRepository.save(savedBook);

        assertEquals(39.99, updatedBook.getPrice(), 0.01);
        assertEquals("J.K. Rowling", updatedBook.getAuthor());
        assertEquals(25, updatedBook.getQuantity());

        String toString = updatedBook.toString();
        assertTrue(toString.contains("Book"));
        assertTrue(toString.contains("J.K. Rowling"));

        bookRepository.delete(updatedBook);
        assertFalse(bookRepository.existsById(updatedBook.getId()));
    }

    @Test
    @Order(3)
    void testMagazineEntityCRUD() {
        Faker faker = getFaker();

        // CREATE
        MagazineEntity magazine = new MagazineEntity();
        magazine.setTitle(faker.book().title() + " Magazine");
        magazine.setPrice(faker.number().randomDouble(2, 5, 15));
        magazine.setQuantity(faker.number().numberBetween(10, 100));
        magazine.setOrderQty(50);
        magazine.setCurrentIssue(LocalDateTime.now());

        MagazineEntity savedMagazine = magazineRepository.save(magazine);

        // READ
        assertNotNull(savedMagazine.getId());
        List<MagazineEntity> allMagazines = magazineRepository.findAll();
        assertFalse(allMagazines.isEmpty());
        assertTrue(allMagazines.stream().anyMatch(m -> m.getId().equals(savedMagazine.getId())));

        // UPDATE
        savedMagazine.setOrderQty(75);
        savedMagazine.setPrice(9.99);
        savedMagazine.setCurrentIssue(LocalDateTime.of(2024, 3, 15, 10, 30));
        MagazineEntity updatedMagazine = magazineRepository.save(savedMagazine);

        assertEquals(75, updatedMagazine.getOrderQty());
        assertEquals(9.99, updatedMagazine.getPrice(), 0.01);
        assertEquals(LocalDateTime.of(2024, 3, 15, 10, 30), updatedMagazine.getCurrentIssue());

        // Test toString
        String toString = updatedMagazine.toString();
        assertTrue(toString.contains("Mag"));
        assertTrue(toString.contains("2024"));

        // DELETE
        magazineRepository.delete(updatedMagazine);
        Optional<MagazineEntity> deleted = magazineRepository.findById(updatedMagazine.getId());
        assertFalse(deleted.isPresent());
    }

    @Test
    @Order(4)
    void testDiscMagEntityCRUD() {
        Faker faker = getFaker();

        // CREATE
        DiscMagEntity discMag = new DiscMagEntity();
        discMag.setTitle(faker.book().title() + " Disc Magazine");
        discMag.setPrice(faker.number().randomDouble(2, 8, 20));
        discMag.setQuantity(faker.number().numberBetween(5, 50));
        discMag.setOrderQty(30);
        discMag.setCurrentIssue(LocalDateTime.now());
        discMag.setHasDisc(true);

        DiscMagEntity savedDiscMag = discMagRepository.save(discMag);

        assertNotNull(savedDiscMag.getId());
        assertTrue(savedDiscMag.isHasDisc());

        savedDiscMag.setHasDisc(false);
        savedDiscMag.setPrice(12.99);
        DiscMagEntity updatedDiscMag = discMagRepository.save(savedDiscMag);

        assertFalse(updatedDiscMag.isHasDisc());
        assertEquals(12.99, updatedDiscMag.getPrice(), 0.01);

        String toString = updatedDiscMag.toString();
        assertTrue(toString.contains("DiscMag"));
        assertTrue(toString.contains("disc=false"));

        discMagRepository.delete(updatedDiscMag);
        assertFalse(discMagRepository.existsById(updatedDiscMag.getId()));
    }

    @Test
    @Order(5)
    void testTicketEntityCRUD() {
        Faker faker = getFaker();

        TicketEntity ticket = new TicketEntity();
        ticket.setTitle("Concert Ticket");
        ticket.setDescription(faker.lorem().sentence());
        ticket.setPrice(faker.number().randomDouble(2, 50, 200));
        ticket.setQuantity(faker.number().numberBetween(1, 100));

        TicketEntity savedTicket = ticketRepository.save(ticket);

        assertNotNull(savedTicket.getId());
        Optional<TicketEntity> foundTicket = ticketRepository.findById(savedTicket.getId());
        assertTrue(foundTicket.isPresent());
        assertEquals("Concert Ticket", foundTicket.get().getTitle());
        assertNotNull(foundTicket.get().getDescription());

        savedTicket.setDescription("VIP Backstage Pass - " + faker.artist().name());
        savedTicket.setPrice(299.99);
        savedTicket.setQuantity(10);
        TicketEntity updatedTicket = ticketRepository.save(savedTicket);

        assertTrue(updatedTicket.getDescription().contains("VIP"));
        assertEquals(299.99, updatedTicket.getPrice(), 0.01);
        assertEquals(10, updatedTicket.getQuantity());

        updatedTicket.sellItem();

        ticketRepository.delete(updatedTicket);
        assertFalse(ticketRepository.existsById(updatedTicket.getId()));
    }

    @Test
    @Order(6)
    void testCarEntityCRUD() {
        Faker faker = getFaker();

        CarEntity car = new CarEntity();
        car.setTitle(faker.commerce().productName() + " Car");
        car.setDescription(faker.lorem().sentence());
        car.setPrice(faker.number().randomDouble(2, 20000, 80000));
        car.setQuantity(faker.number().numberBetween(1, 10));
        car.setEngineType("Gasoline");
        car.setNumberOfDoors(4);

        CarEntity savedCar = carRepository.save(car);

        // READ
        assertNotNull(savedCar.getId());
        Optional<CarEntity> foundCar = carRepository.findById(savedCar.getId());
        assertTrue(foundCar.isPresent());
        assertEquals("Gasoline", foundCar.get().getEngineType());
        assertEquals(4, foundCar.get().getNumberOfDoors());

        // UPDATE
        savedCar.setEngineType("Electric");
        savedCar.setNumberOfDoors(2);
        savedCar.setPrice(44990.00);
        CarEntity updatedCar = carRepository.save(savedCar);

        assertEquals("Electric", updatedCar.getEngineType());
        assertEquals(2, updatedCar.getNumberOfDoors());
        assertEquals(44990.00, updatedCar.getPrice(), 0.01);

        // Test SaleableItem methods
        int initialQuantity = updatedCar.getQuantity();
        updatedCar.sellItem(); // Sell one
        assertEquals(initialQuantity - 1, updatedCar.getQuantity());

        assertTrue(updatedCar.sell(2)); // Sell two more
        assertEquals(initialQuantity - 3, updatedCar.getQuantity());

        // Test toString
        String toString = updatedCar.toString();
        assertTrue(toString.contains("CarEntity"));
        assertTrue(toString.contains("Electric"));
        assertTrue(toString.contains("numberOfDoors=2"));

        // DELETE
        carRepository.delete(updatedCar);
        assertFalse(carRepository.existsById(updatedCar.getId()));
    }

    @Test
    @Order(7)
    void testMotorcycleEntityCRUD() {
        Faker faker = getFaker();

        // CREATE
        MotorcycleEntity motorcycle = new MotorcycleEntity();
        motorcycle.setTitle(faker.commerce().productName() + " Motorcycle");
        motorcycle.setDescription(faker.lorem().sentence());
        motorcycle.setPrice(faker.number().randomDouble(2, 5000, 25000));
        motorcycle.setQuantity(faker.number().numberBetween(1, 20));
        motorcycle.setEngineType("V-Twin");
        motorcycle.setHasSidecar(false);

        MotorcycleEntity savedMotorcycle = motorcycleRepository.save(motorcycle);

        // READ
        assertNotNull(savedMotorcycle.getId());
        List<MotorcycleEntity> allMotorcycles = motorcycleRepository.findAll();
        assertFalse(allMotorcycles.isEmpty());

        // UPDATE
        savedMotorcycle.setEngineType("Inline-4");
        savedMotorcycle.setHasSidecar(true);
        savedMotorcycle.setPrice(15999.00);
        MotorcycleEntity updatedMotorcycle = motorcycleRepository.save(savedMotorcycle);

        assertEquals("Inline-4", updatedMotorcycle.getEngineType());
        assertTrue(updatedMotorcycle.getHasSidecar());
        assertEquals(15999.00, updatedMotorcycle.getPrice(), 0.01);

        // Test SaleableItem methods
        assertTrue(updatedMotorcycle.sell(2));
        assertEquals(updatedMotorcycle.getQuantity(), savedMotorcycle.getQuantity() - 2);

        updatedMotorcycle.sellItem(); // Sell one more
        assertEquals(savedMotorcycle.getQuantity() - 3, updatedMotorcycle.getQuantity());

        // Test toString
        String toString = updatedMotorcycle.toString();
        assertTrue(toString.contains("MotorcycleEntity"));
        assertTrue(toString.contains("hasSidecar=true"));
        assertTrue(toString.contains("Inline-4"));

        // DELETE
        motorcycleRepository.delete(updatedMotorcycle);
        assertFalse(motorcycleRepository.existsById(updatedMotorcycle.getId()));
    }

    @Test
    @Order(8)
    void testVehicleRepositoryCRUD() {
        Faker faker = getFaker();

        long initialCount = vehicleRepository.count();

        CarEntity car = new CarEntity();
        car.setTitle("Toyota Camry");
        car.setDescription("A reliable sedan car");
        car.setPrice(25000.0);
        car.setQuantity(10);
        car.setEngineType("V6");
        car.setNumberOfDoors(4);
        carRepository.save(car);

        MotorcycleEntity motorcycle = new MotorcycleEntity();
        motorcycle.setTitle("Test Motorcycle");
        motorcycle.setDescription("A test motorcycle");  // ADD THIS!
        motorcycle.setPrice(15000.00);
        motorcycle.setQuantity(5);
        motorcycle.setEngineType("Single Cylinder");
        motorcycle.setHasSidecar(false);
        motorcycleRepository.save(motorcycle);

        // Check total count increased by 2
        assertEquals(initialCount + 2, vehicleRepository.count());

        // Get ALL vehicles (including existing ones)
        List<VehicleEntity> allVehicles = vehicleRepository.findAll();

        // Count only the NEW vehicles we just created
        List<VehicleEntity> newVehicles = allVehicles.stream()
                .filter(v -> v.getTitle().equals("Toyota Camry") ||
                        v.getTitle().equals("Test Motorcycle"))
                .toList();

        assertEquals(2, newVehicles.size());

        long carCount = newVehicles.stream().filter(v -> v instanceof CarEntity).count();
        long motorcycleCount = newVehicles.stream().filter(v -> v instanceof MotorcycleEntity).count();
        assertEquals(1, carCount);
        assertEquals(1, motorcycleCount);

        for (VehicleEntity vehicle : newVehicles) {
            assertNotNull(vehicle.getEngineType());
            assertTrue(vehicle.getPrice() > 0);

            // Test selling
            int initialQty = vehicle.getQuantity();
            vehicle.sellItem();
            assertEquals(initialQty - 1, vehicle.getQuantity());
        }
    }

    @Test
    @Order(9)
    @Transactional
    void testCartEntityCRUD() {
        Faker faker = getFaker();

        BookEntity book = new BookEntity();
        book.setTitle(faker.book().title());
        book.setPrice(25.99);
        book.setQuantity(10);
        book.setAuthor(faker.book().author());
        bookRepository.save(book);

        CarEntity car = new CarEntity();
        car.setTitle(faker.commerce().productName() + " Car");
        car.setPrice(35000.00);
        car.setQuantity(2);
        car.setEngineType("Hybrid");
        car.setNumberOfDoors(4);
        carRepository.save(car);

        TicketEntity ticket = new TicketEntity();
        ticket.setTitle("Sports Event");
        ticket.setDescription("Final Championship Game");
        ticket.setPrice(150.00);
        ticket.setQuantity(50);
        ticketRepository.save(ticket);

        // CREATE Cart
        CartEntity cart = new CartEntity();
        cart.setProducts(new HashSet<>());

        CartEntity savedCart = cartRepository.save(cart);
        assertNotNull(savedCart.getId());

        // ADD Products to Cart using addProduct method
        savedCart.addProduct(book);
        savedCart.addProduct(car);
        savedCart.addProduct(ticket);
        cartRepository.save(savedCart);

        // READ and VERIFY
        Optional<CartEntity> foundCart = cartRepository.findById(savedCart.getId());
        assertTrue(foundCart.isPresent());
        assertEquals(3, foundCart.get().getProducts().size());

        // Verify bidirectional relationship
        for (ProductEntity product : foundCart.get().getProducts()) {
            assertTrue(product.getCarts().contains(foundCart.get()));
        }

        // Test removing a product
        CartEntity cartToUpdate = foundCart.get();
        cartToUpdate.getProducts().remove(car);
        cartRepository.save(cartToUpdate);

        Optional<CartEntity> updatedCart = cartRepository.findById(savedCart.getId());
        assertTrue(updatedCart.isPresent());
        assertEquals(2, updatedCart.get().getProducts().size());

        assertFalse(updatedCart.get().getProducts().contains(car));

        cartRepository.delete(updatedCart.get());
        assertFalse(cartRepository.existsById(savedCart.getId()));

        assertTrue(productRepository.existsById(book.getId()));
        assertTrue(productRepository.existsById(car.getId()));
        assertTrue(productRepository.existsById(ticket.getId()));
    }


    @Test
    @Order(10)
    void testEqualsAndHashCode() {
        BookEntity book1 = new BookEntity("Test Book", "Test Description", 29.99, 10, "Test Author");
        BookEntity book2 = new BookEntity("Test Book1", "Test Description1", 29.99, 10, "Test Author1");
        BookEntity book3 = new BookEntity("Different Book", "Different Description", 39.99, 5, "Different Author");
        assertEquals(book1, book1);
        assertNotEquals(book1, book3);
        assertEquals(book1.hashCode(), book1.hashCode());
        assertNotEquals(book1.hashCode(), book3.hashCode());

        LocalDateTime now = LocalDateTime.now();
        MagazineEntity mag1 = new MagazineEntity("Magazine 1", "Monthly magazine", 9.99, 20, 50, now);
        MagazineEntity mag2 = new MagazineEntity("Magazine 1", "Monthly magazine", 9.99, 20, 50, now);
        MagazineEntity mag3 = new MagazineEntity("Magazine 2", "Bi-weekly magazine", 12.99, 15, 75, now);
        assertEquals(mag1, mag2);
        assertNotEquals(mag1, mag3);

        DiscMagEntity discMag1 = new DiscMagEntity("DiscMag 1", "Magazine with CD", 14.99, 10, 30, now, true);
        DiscMagEntity discMag2 = new DiscMagEntity("DiscMag 1", "Magazine with CD", 14.99, 10, 30, now, true);
        DiscMagEntity discMag3 = new DiscMagEntity("DiscMag 2", "Magazine without CD", 16.99, 8, 25, now, false);
        assertEquals(discMag1, discMag2);
        assertNotEquals(discMag1, discMag3);
    }
}