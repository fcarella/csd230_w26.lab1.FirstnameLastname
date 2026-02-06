package csd230.lab1;

import csd230.lab1.entities.*;
import csd230.lab1.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final LaptopRepository laptopRepository;
    private final PhoneRepository phoneRepository;
    private final CartRepository cartRepository;

    public Application(
            LaptopRepository laptopRepository,
            PhoneRepository phoneRepository,
            CartRepository cartRepository
    ) {
        this.laptopRepository = laptopRepository;
        this.phoneRepository = phoneRepository;
        this.cartRepository = cartRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) {

        System.out.println("====================================");
        System.out.println(" SAVING NICHE ITEMS ");
        System.out.println("====================================");

        // ----------- NICHE ITEM: LAPTOP -----------
        LaptopEntity laptop = new LaptopEntity();
        laptop.setBrand("Dell XPS");
        laptop.setPrice(999.99);
        laptop.setRamGb(16);

        laptopRepository.save(laptop);

        System.out.println("Saved Laptop ID: " + laptop.getId());

        // ----------- NICHE ITEM: PHONE -----------
        PhoneEntity phone = new PhoneEntity();
        phone.setBrand("Pixel 8");
        phone.setPrice(799.99);
        phone.setHas5g(true);

        phoneRepository.save(phone);

        System.out.println("Saved Phone ID: " + phone.getId());

        System.out.println("====================================");
        System.out.println(" SELLING NICHE ITEMS (CART DEMO) ");
        System.out.println("====================================");

        // ----------- MANY-TO-MANY SALE -----------
        CartEntity cart = new CartEntity();
        cart.getProducts().add(laptop);
        cart.getProducts().add(phone);

        cartRepository.save(cart);

        System.out.println("Cart ID: " + cart.getId());
        System.out.println("Products sold in cart:");

        cart.getProducts().forEach(p ->
                System.out.println(" - Product ID: " + p.getId() + " ($" + p.getPrice() + ")")
        );

        System.out.println("====================================");
        System.out.println(" APPLICATION FINISHED SUCCESSFULLY ");
        System.out.println("====================================");
    }
}
