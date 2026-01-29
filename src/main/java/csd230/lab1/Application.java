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
import csd230.lab1.services.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final ProductEntityRepository productRepository;
    private final CartEntityRepository cartRepository;
    private final OrderService orderService;

    public Application(ProductEntityRepository productRepository,
                       CartEntityRepository cartRepository,
                       OrderService orderService) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.orderService = orderService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) {

        System.out.println("===== START LAB 1 COMMANDLINERUNNER =====");

        Faker faker = new Faker();

        // 1) Create a Cart and save it first
        CartEntity cart = new CartEntity();
        cartRepository.save(cart);
        System.out.println("Created cart with id: " + cart.getId());

        // 2) Create products
        double bookPrice = Double.parseDouble(faker.commerce().price());

        BookEntity book = new BookEntity(
                faker.book().title(),
                bookPrice,
                10,
                faker.book().author()
        );

        MagazineEntity magazine = new MagazineEntity(
                faker.lorem().word() + " Magazine",
                12.99,
                20,
                50,
                LocalDateTime.now()
        );

        LaptopEntity laptop = new LaptopEntity("Dell", 16);
        SmartphoneEntity smartphone = new SmartphoneEntity("Samsung", 48);

        // 3) Save products
        productRepository.save(book);
        productRepository.save(magazine);
        productRepository.save(laptop);
        productRepository.save(smartphone);

        // 4) Add products to cart (Many-to-Many) and save cart
        cart.addProduct(book);
        cart.addProduct(magazine);
        cart.addProduct(laptop);
        cart.addProduct(smartphone);
        cartRepository.save(cart);


        // 5) Print cart content (proof of Many-to-Many)
        System.out.println("=== CART CONTENT (MANY-TO-MANY PROOF) ===");
        System.out.println("Cart ID: " + cart.getId());
        for (ProductEntity p : cart.getProducts()) {
            System.out.println("  -> Product ID: " + p.getId() + " | " + p);
        }

        // 6) Print all products
        System.out.println("=== ALL PRODUCTS ===");
        List<ProductEntity> allProducts = productRepository.findAll();
        for (ProductEntity p : allProducts) {
            System.out.println(p);
        }

        // 7) Print all carts and their products
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
