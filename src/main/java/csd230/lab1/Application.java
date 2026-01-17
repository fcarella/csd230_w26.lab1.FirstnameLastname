package csd230.lab1;

import com.github.javafaker.Faker;
import csd230.lab1.entities.BookEntity;
import csd230.lab1.entities.CartEntity;
import csd230.lab1.entities.LaptopEntity;
import csd230.lab1.entities.MagazineEntity;
import csd230.lab1.entities.ProductEntity;
import csd230.lab1.entities.SmartphoneEntity;
import csd230.lab1.repositories.CartEntityRepository;
import csd230.lab1.repositories.ProductEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    // Repositories injected by constructor
    private final ProductEntityRepository productRepository;
    private final CartEntityRepository cartRepository;

    // Constructor injection
    public Application(ProductEntityRepository productRepository,
                       CartEntityRepository cartRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // Runs once when the application starts
    @Override
    @Transactional
    public void run(String... args) {

        System.out.println("===== START LAB 1 COMMANDLINERUNNER =====");

        // 1) Create Faker helper
        Faker faker = new Faker();

        // 2) Create a Cart and save it first
        CartEntity cart = new CartEntity();
        cartRepository.save(cart);
        System.out.println("Created cart with id: " + cart.getId());

        // 3) Create base products (Book + Magazine)
        double bookPrice = Double.parseDouble(faker.commerce().price());

        BookEntity book = new BookEntity(
                faker.book().title(),    // title
                bookPrice,               // price
                10,                      // copies/stock
                faker.book().author()    // author
        );

        MagazineEntity magazine = new MagazineEntity(
                faker.lorem().word() + " Magazine", // title
                12.99,                              // price
                20,                                 // copies/stock
                50,                                 // orderQty / issueNumber (depends on your entity)
                LocalDateTime.now()                 // current issue date
        );

        // 4) Create niche products (Electronics)
        // IMPORTANT: Using YOUR constructors:
        // LaptopEntity(String brand, int ramGb)
        // SmartphoneEntity(String brand, int cameraMegapixels)
        LaptopEntity laptop = new LaptopEntity("Dell", 16);
        SmartphoneEntity smartphone = new SmartphoneEntity("Samsung", 48);

        // 5) Save products (simple + explicit)
        productRepository.save(book);
        productRepository.save(magazine);
        productRepository.save(laptop);
        productRepository.save(smartphone);

        // 6) Add products to the cart (Many-to-Many)
        cart.addProduct(book);
        cart.addProduct(magazine);
        cart.addProduct(laptop);
        cart.addProduct(smartphone);
        cartRepository.save(cart);

        // print: clear proof of Many-to-Many in the console (for screenshot #5)
        System.out.println("=== CART CONTENT (MANY-TO-MANY PROOF) ===");
        System.out.println("Cart ID: " + cart.getId());
        for (ProductEntity p : cart.getProducts()) {
            System.out.println("  -> Product ID: " + p.getId() + " | " + p);
        }

        // 7) Print all products
        System.out.println("=== ALL PRODUCTS ===");
        List<ProductEntity> allProducts = productRepository.findAll();
        for (ProductEntity p : allProducts) {
            System.out.println(p);
        }

        // 8) Print all carts and their products
        System.out.println("=== ALL CARTS AND THEIR PRODUCTS ===");
        List<CartEntity> allCarts = cartRepository.findAll();
        for (CartEntity c : allCarts) {
            System.out.println("Cart: " + c);
            for (ProductEntity p : c.getProducts()) {
                System.out.println("  -> " + p);
            }
        }

        System.out.println("===== END LAB 1 COMMANDLINERUNNER =====");
    }
}
