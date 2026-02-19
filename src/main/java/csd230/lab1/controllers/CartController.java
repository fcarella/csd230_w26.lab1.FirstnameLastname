package csd230.lab1.controllers;

import csd230.lab1.entities.*;
import csd230.lab1.repositories.BookRepository;
import csd230.lab1.repositories.CartRepository;
import csd230.lab1.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    // View cart
    @GetMapping
    public String viewCart(Model model) {
        Long defaultCartId = 1L;

        CartEntity cart = cartRepository.findById(defaultCartId)
                .orElseGet(() -> cartRepository.save(new CartEntity()));

        model.addAttribute("cart", cart);
        return "cartDetails";
    }

    // Add book to cart
    @GetMapping("/add/{bookId}")
    public String addToCart(@PathVariable Long bookId) {
        Long defaultCartId = 1L;

        CartEntity cart = cartRepository.findById(defaultCartId).orElse(null);
        BookEntity book = bookRepository.findById(bookId.longValue());

        if (cart != null && book != null) {
            cart.addProduct(book);
            cartRepository.save(cart);
        }

        return "redirect:/books";
    }

    // Remove book from cart
    @GetMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId) {
        Long defaultCartId = 1L;

        CartEntity cart = cartRepository.findById(defaultCartId).orElse(null);
        if (cart == null) return "redirect:/cart";

        // remove by id
        cart.getProducts().removeIf(p -> p.getId().equals(productId));
        cartRepository.save(cart);

        return "redirect:/cart";
    }

    // ✅ CHECKOUT (Lab 2)
    @PostMapping("/checkout")
    public String checkout(Model model) {
        Long defaultCartId = 1L;
        CartEntity cart = cartRepository.findById(defaultCartId).orElse(null);

        if (cart == null || cart.getProducts().isEmpty()) {
            model.addAttribute("cart", cart);
            model.addAttribute("error", "Cart is empty. Add items before checkout.");
            return "cartDetails";
        }

        OrderEntity order = new OrderEntity();
        order.setOrderDate(LocalDateTime.now());

        double total = 0.0;

        // copy so we can clear cart safely
        Set<ProductEntity> items = Set.copyOf(cart.getProducts());

        for (ProductEntity product : items) {
            total += product.getPrice();

            // decrement stock if it's a publication (Book/Magazine)
            if (product instanceof PublicationEntity pub) {
                if (pub.getCopies() > 0) {
                    pub.setCopies(pub.getCopies() - 1);
                }
            }

            order.addProduct(product);
        }

        order.setTotalAmount(total);

        // clear cart
        cart.getProducts().clear();

        // save
        orderRepository.save(order);
        cartRepository.save(cart);

        // save updated books (so copies persists)
        for (ProductEntity product : items) {
            if (product instanceof BookEntity book) {
                bookRepository.save(book);
            }
        }

        model.addAttribute("order", order);
        return "orderDetails";
    }
}
