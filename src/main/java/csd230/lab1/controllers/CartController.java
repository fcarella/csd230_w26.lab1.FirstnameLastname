package csd230.lab1.controllers;

import csd230.lab1.entities.BookEntity;
import csd230.lab1.entities.CartEntity;
import csd230.lab1.entities.UserEntity;
import csd230.lab1.repositories.BookEntityRepository;
import csd230.lab1.repositories.CartEntityRepository;
import csd230.lab1.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartEntityRepository cartRepository;

    @Autowired
    private BookEntityRepository bookRepository;

    @Autowired
    private UserEntityRepository userRepository;

    // 1) View cart (per logged-in user)
    @GetMapping
    public String viewCart(Model model, Principal principal) {

        String username = principal.getName();

        CartEntity cart = cartRepository.findByUserUsername(username)
                .orElseGet(() -> {
                    UserEntity user = userRepository.findByUsername(username).orElseThrow();
                    CartEntity newCart = new CartEntity();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        model.addAttribute("cart", cart);
        return "cartDetails";
    }

    // 2) Add book to the logged-in user's cart
    @GetMapping("/add/{bookId}")
    public String addToCart(@PathVariable Long bookId, Principal principal) {

        String username = principal.getName();

        CartEntity cart = cartRepository.findByUserUsername(username).orElse(null);
        BookEntity book = bookRepository.findById(bookId).orElse(null);

        if (cart != null && book != null) {
            cart.addProduct(book);
            cartRepository.save(cart);
        }

        return "redirect:/books";
    }

    // 3) Remove book from the logged-in user's cart
    @GetMapping("/remove/{bookId}")
    public String removeFromCart(@PathVariable Long bookId, Principal principal) {

        String username = principal.getName();

        CartEntity cart = cartRepository.findByUserUsername(username).orElse(null);
        BookEntity book = bookRepository.findById(bookId).orElse(null);

        if (cart != null && book != null) {
            cart.getProducts().remove(book);
            cartRepository.save(cart);
        }

        return "redirect:/cart";
    }
}
