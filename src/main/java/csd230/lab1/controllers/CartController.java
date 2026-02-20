package csd230.lab1.controllers;

import csd230.lab1.entities.*;
import csd230.lab1.repositories.BookRepository;
import csd230.lab1.repositories.CartRepository;
import csd230.lab1.repositories.OrderRepository;
import csd230.lab1.repositories.UserEntityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Set;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartRepository cartRepository;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final UserEntityRepository userRepository;

    public CartController(CartRepository cartRepository,
                          BookRepository bookRepository,
                          OrderRepository orderRepository,
                          UserEntityRepository userRepository) {
        this.cartRepository = cartRepository;
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    // ✅ Helper: get cart for logged-in user (create if missing)
    private CartEntity getCartForCurrentUser(Principal principal) {
        String username = principal.getName();
        UserEntity user = userRepository.findByUsername(username);

        CartEntity cart = cartRepository.findByUser(user);

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
        BookEntity book = bookRepository.findById(bookId.longValue());

        if (book != null) {
            cart.addProduct(book);
            cartRepository.save(cart);
        }

        return "redirect:/books";
    }

    @GetMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId, Principal principal) {
        CartEntity cart = getCartForCurrentUser(principal);

        cart.getProducts().removeIf(p -> p.getId().equals(productId));
        cartRepository.save(cart);

        return "redirect:/cart";
    }

    // ✅ CHECKOUT (Lab 2) updated to use user-specific cart
    @PostMapping("/checkout")
    public String checkout(Model model, Principal principal) {
        CartEntity cart = getCartForCurrentUser(principal);

        if (cart.getProducts().isEmpty()) {
            model.addAttribute("cart", cart);
            model.addAttribute("error", "Cart is empty. Add items before checkout.");
            return "cartDetails";
        }

        OrderEntity order = new OrderEntity();
        order.setOrderDate(LocalDateTime.now());

        double total = 0.0;

        Set<ProductEntity> items = Set.copyOf(cart.getProducts());

        for (ProductEntity product : items) {
            total += product.getPrice();

            if (product instanceof PublicationEntity pub) {
                if (pub.getCopies() > 0) {
                    pub.setCopies(pub.getCopies() - 1);
                }
            }

            order.addProduct(product);
        }

        order.setTotalAmount(total);

        cart.getProducts().clear();

        orderRepository.save(order);
        cartRepository.save(cart);

        for (ProductEntity product : items) {
            if (product instanceof BookEntity book) {
                bookRepository.save(book);
            }
        }

        model.addAttribute("order", order);
        return "orderDetails";
    }
}
