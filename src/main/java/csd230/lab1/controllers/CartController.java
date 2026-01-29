package csd230.lab1.controllers;

import csd230.lab1.entities.CartEntity;
import csd230.lab1.repositories.CartEntityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

    private final CartEntityRepository cartRepository;

    public CartController(CartEntityRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @GetMapping("/cart")
    public String cartDetails(Model model) {

        CartEntity cart = cartRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No cart found. Run Application once to create one."));

        model.addAttribute("cart", cart);
        return "cartDetails";
    }

}
