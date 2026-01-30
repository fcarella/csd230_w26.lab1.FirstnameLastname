package csd230.lab1.controllers;
import java.security.Principal;import csd230.lab1.entities.BookEntity;
import csd230.lab1.entities.CartEntity;
import csd230.lab1.entities.UserEntity;
import csd230.lab1.repositories.UserRepository;import csd230.lab1.repositories.BookRepository;
import csd230.lab1.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    private CartEntity getCartForCurrentUser(Principal principal) {
        // 1. Get username from security context
        String username = principal.getName();
        // 2. Find User in DB
        UserEntity user = userRepository.findByUsername(username);

        // 3. Find Cart for this User
        CartEntity cart = cartRepository.findByUser(user);

        // 4. If no cart exists, create one and link it to the user
        if (cart == null) {
            cart = new CartEntity();
            cart.setUser(user);
            cartRepository.save(cart);
        }
        return cart;
    }
    @GetMapping
    public String viewCart(Model model, Principal principal) {
        CartEntity cart = getCartForCurrentUser(principal);
        model.addAttribute("cart", cart);
        return "cartDetails";
    }
    @GetMapping("/add/{bookId}")
    public String addToCart(@PathVariable Long bookId, Principal principal) {
        CartEntity cart = getCartForCurrentUser(principal);
        BookEntity book = bookRepository.findById(bookId).orElse(null);
        if (book != null) {
            cart.addProduct(book);
            cartRepository.save(cart);
        }
        return "redirect:/books";
    }
    @GetMapping("/remove/{bookId}")
    public String removeFromCart(@PathVariable Long bookId, Principal principal) {
        CartEntity cart = getCartForCurrentUser(principal);
        BookEntity book = bookRepository.findById(bookId).orElse(null);

        if (book != null) {
            cart.getProducts().remove(book);
            cartRepository.save(cart);
        }
        return "redirect:/cart";
    }
}

