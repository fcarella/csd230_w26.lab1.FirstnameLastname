package csd230.lab1;
import com.github.javafaker.Commerce;
import com.github.javafaker.Faker;
import csd230.lab1.entities.*;
import csd230.lab1.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class Application  implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CarRepository carRepository;
    private final MotorcycleRepository motorcycleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrderRepository orderRepository;

    public Application(ProductRepository productRepository,
                       CartRepository cartRepository,
                       CarRepository carRepository,
                       MotorcycleRepository motorcycleRepository,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.carRepository = carRepository;
        this.motorcycleRepository = motorcycleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.orderRepository = orderRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // CREATE USERS (Lecture 2.6)
        // Admin User (Can Add/Edit/Delete)
        UserEntity admin = new UserEntity("admin", passwordEncoder.encode("admin"), "ADMIN");
        userRepository.save(admin);

        // Regular User (Can only View/Buy)
        UserEntity user = new UserEntity("user", passwordEncoder.encode("user"), "USER");
        userRepository.save(user);

        System.out.println("Default users created: admin/admin and user/user");

        // ALL YOUR EXISTING CODE BELOW (kept exactly as is)
        Faker faker = new Faker();
        Commerce cm = faker.commerce();
        com.github.javafaker.Number number = faker.number();
        com.github.javafaker.Book fakeBook = faker.book();
        String name = cm.productName();
        String description = cm.material();
        String priceString = faker.commerce().price();

        BookEntity book = new BookEntity(
                "The Great Gatsby",
                "A classic novel about the American Dream",
                12.99,
                50,
                "F. Scott Fitzgerald"
        );

        MagazineEntity magazine = new MagazineEntity(
                "Tech Monthly",
                "Monthly tech magazine",
                9.99,
                100,
                50,
                LocalDateTime.now()
        );

        CartEntity cart = new CartEntity();
        cartRepository.save(cart);

        // add book to the cart
        cart.addProduct(book);
        cartRepository.save(cart);

        cart.addProduct(magazine);
        cartRepository.save(cart);

        List<ProductEntity> allProducts = productRepository.findAll();
        System.out.println("\n=== All Products ===");
        for(ProductEntity p : allProducts) {
            System.out.println(p.toString());
        }

        List<CartEntity> allCarts = cartRepository.findAll();
        System.out.println("\n=== All Carts with Products ===");
        for(CartEntity c : allCarts) {
            System.out.println(c.toString());
            for(ProductEntity p : c.getProducts()) {
                System.out.println("  - " + p.toString());
            }
        }
        System.out.println("\n=== My Niche: Vehicles Hierarchy ===");
        System.out.println("Demonstrating CarEntity and MotorcycleEntity extending VehicleEntity\n");

        CarEntity car = new CarEntity();
        car.setTitle(faker.company().name() + " Sedan");
        car.setPrice(Double.parseDouble(faker.commerce().price(20000, 50000)));
        car.setQuantity(3);
        car.setDescription(faker.lorem().sentence());
        car.setEngineType("Hybrid");
        car.setNumberOfDoors(4);

        carRepository.save(car);
        System.out.println("✓ Created Car: " + car.getTitle());
        System.out.println("  - Engine: " + car.getEngineType());
        System.out.println("  - Doors: " + car.getNumberOfDoors());
        System.out.println("  - Price: $" + car.getPrice());

        MotorcycleEntity motorcycle = new MotorcycleEntity();
        motorcycle.setTitle(faker.company().name() + " Sport Bike");
        motorcycle.setPrice(Double.parseDouble(faker.commerce().price(8000, 20000)));
        motorcycle.setQuantity(5);
        motorcycle.setDescription(faker.lorem().sentence());
        motorcycle.setEngineType("Gasoline");
        motorcycle.setHasSidecar(faker.bool().bool());

        motorcycleRepository.save(motorcycle);
        System.out.println("\n✓ Created Motorcycle: " + motorcycle.getTitle());
        System.out.println("  - Engine: " + motorcycle.getEngineType());
        System.out.println("  - Has Sidecar: " + motorcycle.getHasSidecar());
        System.out.println("  - Price: $" + motorcycle.getPrice());

        cart.addProduct(car);
        cart.addProduct(motorcycle);
        cartRepository.save(cart);
        System.out.println("\n✓ Added Car and Motorcycle to the cart");

        System.out.println("\n=== Demonstrating SaleableItem Interface ===");
        System.out.println("Car quantity before sellItem(): " + car.getQuantity());
        car.sellItem();
        System.out.println("Car quantity after sellItem(): " + car.getQuantity());

        System.out.println("\nMotorcycle quantity before sell(2): " + motorcycle.getQuantity());
        boolean sold = motorcycle.sell(2);
        System.out.println("Sold 2 motorcycles? " + sold);
        System.out.println("Motorcycle quantity after sell(2): " + motorcycle.getQuantity());

        allProducts = productRepository.findAll();
        System.out.println("\n=== All Products (Including Vehicles) ===");
        System.out.println("Total products in database: " + allProducts.size());
        for(ProductEntity p : allProducts) {
            String type = p.getClass().getSimpleName();
            System.out.println("  - " + type + ": " +
                    (p.getTitle() != null ? p.getTitle() : "No title") +
                    " ($" + p.getPrice() + ")");
        }

        System.out.println("\n=== Final Cart Contents ===");
        CartEntity finalCart = cartRepository.findById(cart.getId()).orElse(null);
        if (finalCart != null) {
            System.out.println("Cart ID: " + finalCart.getId());
            System.out.println("Total items in cart: " + finalCart.getProducts().size());
            System.out.println("\nItems in cart:");
            for(ProductEntity p : finalCart.getProducts()) {
                String type = p.getClass().getSimpleName();
                System.out.println("  - " + type + ": " +
                        (p.getTitle() != null ? p.getTitle() : "No title") +
                        " ($" + p.getPrice() + ")");
            }
        }
    }
}