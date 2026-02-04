package csd230.lab1.controllers;

import csd230.lab1.entities.*;
import csd230.lab1.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartEntityRepository cartRepository;

    @Autowired
    private BookEntityRepository bookRepository;

    @Autowired
    private OrderEntityRepository orderRepository;

    @Autowired
    private ProductEntityRepository productRepository;

    @Autowired
    private MagazineEntityRepository magazineRepository;

    @Autowired
    private DiscMagEntityRepository discMagEntityRepository;

    @Autowired
    private FoundationEntityRepository foundationRepository;

    @Autowired
    private LipstickEntityRepository lipstickRepository;

    @Autowired
    private TicketEntityRepository ticketRepository;

    @GetMapping
    public String viewCart(Model model) {
        Long defaultCartId = 1L;

        CartEntity cart = cartRepository.findById(defaultCartId)
                .orElseGet(() -> {
                    CartEntity newCart = new CartEntity();
                    newCart.setId(defaultCartId);
                    return cartRepository.save(newCart);
                });

        double totalAmount = 0.0;
        for (ProductEntity product : cart.getProducts()) {
            totalAmount += product.getPrice();
        }

        model.addAttribute("cart", cart);
        model.addAttribute("totalAmount", totalAmount);
        return "cartDetails";
    }

    @GetMapping("/add/{bookId}")
    public String addToCart(@PathVariable Long bookId) {
        Long defaultCartId = 1L;
        CartEntity cart = cartRepository.findById(defaultCartId).orElse(null);
        BookEntity book = bookRepository.findById(bookId).orElse(null);

        if (cart != null && book != null) {
            cart.addProduct(book);
            cartRepository.save(cart);
        }
        return "redirect:/books";
    }

    @GetMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId) {
        Long defaultCartId = 1L;
        CartEntity cart = cartRepository.findById(defaultCartId).orElse(null);
        ProductEntity product = productRepository.findById(productId).orElse(null);

        if (cart != null && product != null) {
            cart.removeProduct(product);
            cartRepository.save(cart);
        }
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    @Transactional
    public String checkout(Model model) {
        Long defaultCartId = 1L;
        CartEntity cart = cartRepository.findById(defaultCartId).orElse(null);

        if (cart == null || cart.getProducts().isEmpty()) {
            model.addAttribute("error", "Cart is empty or does not exist");
            return "redirect:/cart";
        }

        OrderEntity order = new OrderEntity();
        order.setOrderDate(LocalDateTime.now());

        double totalAmount = 0.0;
        Set<ProductEntity> processedProducts = new HashSet<>();

        for (ProductEntity product : cart.getProducts()) {
            totalAmount += product.getPrice();

            if (product instanceof PublicationEntity) {
                PublicationEntity publication = (PublicationEntity) product;

                if (publication.getCopies() > 0) {
                    publication.setCopies(publication.getCopies() - 1);

                    if (product instanceof BookEntity) {
                        bookRepository.save((BookEntity) publication);
                    } else if (product instanceof MagazineEntity) {
                        if (product instanceof DiscMagEntity) {
                            discMagEntityRepository.save((DiscMagEntity) publication);
                        } else {
                            magazineRepository.save((MagazineEntity) publication);
                        }
                    }
                } else {
                    continue;
                }
            }

            processedProducts.add(product);
        }

        if (processedProducts.isEmpty()) {
            model.addAttribute("error", "No items available for checkout (out of stock)");
            return "redirect:/cart";
        }

        order.setTotalAmount(totalAmount);
        order.setProducts(processedProducts);

        try {
            OrderEntity savedOrder = orderRepository.save(order);

            cart.getProducts().clear();
            cartRepository.save(cart);

            return "redirect:/order/confirmation/" + savedOrder.getId();

        } catch (Exception e) {
            model.addAttribute("error", "Checkout failed: " + e.getMessage());
            return "redirect:/cart";
        }
    }
}
