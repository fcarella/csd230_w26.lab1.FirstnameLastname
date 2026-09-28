package csd230.lab1;

import csd230.lab1.pojos.Laptop;
import csd230.lab1.pojos.SmartPhone;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Runner class that executes automatically after the Spring Application context is loaded.
 */
@Component
public class CartRunner implements CommandLineRunner {

    @Override
    public void run(String @NonNull ... args) throws Exception {
        System.out.println("--- Spring Boot Application Started ---");

        // Instantiating POJOs
        Laptop laptop = new Laptop(1200.00, "Dell XPS 13", "Dell", 16);
        SmartPhone phone = new SmartPhone(800.00, "Galaxy S23", "Samsung", 6.1);

        System.out.println("Created Laptop: " + laptop);
        System.out.println("Created SmartPhone: " + phone);

        laptop.sellItem();
        phone.sellItem();

    }
}