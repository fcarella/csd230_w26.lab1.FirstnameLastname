package csd230.lab1.controllers;

import csd230.lab1.entities.BookEntity;
import csd230.lab1.entities.CartEntity;
import csd230.lab1.entities.OrderEntity;
import csd230.lab1.entities.ProductEntity;
import csd230.lab1.repositories.BookRepository;
import csd230.lab1.repositories.CartRepository;
import csd230.lab1.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Handle the checkout process (POST request from cart page)
     * This is the main checkout method that performs all business logic
     */
    @PostMapping("/checkout")
    public String checkout(RedirectAttributes redirectAttributes) {
        Long defaultCartId = 1L;

        // Step 1: Retrieve the Cart - Get the default cart (ID 1)
        Optional<CartEntity> cartOptional = cartRepository.findById(defaultCartId);

        if (cartOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Cart not found. Please try again.");
            return "redirect:/cart";
        }

        CartEntity cart = cartOptional.get();

        // Check if cart is empty
        if (cart.getProducts().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Your cart is empty. Add items before checkout.");
            return "redirect:/cart";
        }

        // Step 2: Create the Order
        OrderEntity order = new OrderEntity();
        order.setOrderDate(LocalDateTime.now());
        double totalAmount = 0.0;

        // Step 3: Process Products & Inventory
        // First, check all items have sufficient stock
        for (ProductEntity product : cart.getProducts()) {
            if (product instanceof BookEntity) {
                BookEntity book = (BookEntity) product;
                if (book.getCopies() <= 0) {
                    redirectAttributes.addFlashAttribute("error",
                        "Sorry, '" + book.getTitle() + "' is out of stock. Please remove it from your cart.");
                    return "redirect:/cart";
                }
            }
        }

        // Process each item in the cart
        for (ProductEntity product : cart.getProducts()) {
            // Calculate Total
            totalAmount += product.getPrice();

            // Add product to Order
            order.addProduct(product);

            // Update Stock - only for BookEntity items
            if (product instanceof BookEntity) {
                BookEntity book = (BookEntity) product;
                int currentCopies = book.getCopies();
                book.setCopies(currentCopies - 1);
                bookRepository.save(book); // Save updated stock
            }
        }

        // Set the total amount
        order.setTotalAmount(totalAmount);

        try {
            // Step 4: Clear the Cart
            cart.getProducts().clear();

            // Step 5: Persist Data
            orderRepository.save(order);  // Save the OrderEntity
            cartRepository.save(cart);    // Save the cleared CartEntity

            // Step 6: Redirect to Order Confirmation page
            redirectAttributes.addFlashAttribute("success", "Order placed successfully!");
            return "redirect:/order/confirmation/" + order.getId();

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                "An error occurred during checkout. Please try again.");
            return "redirect:/cart";
        }
    }

    /**
     * Display order confirmation page
     */
    @GetMapping("/confirmation/{orderId}")
    public String showConfirmation(@PathVariable Long orderId, Model model) {
        Optional<OrderEntity> orderOptional = orderRepository.findById(orderId);

        if (orderOptional.isEmpty()) {
            model.addAttribute("error", "Order not found");
            return "redirect:/cart";
        }

        OrderEntity order = orderOptional.get();
        model.addAttribute("order", order);
        return "orderDetails"; // This will render orderDetails.html
    }

    /**
     * Alternative: Simple GET checkout for easier testing
     * This combines checkout and confirmation in one step
     */
    @GetMapping("/checkout-simple")
    public String checkoutSimple(Model model, RedirectAttributes redirectAttributes) {
        Long defaultCartId = 1L;

        Optional<CartEntity> cartOptional = cartRepository.findById(defaultCartId);

        if (cartOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Cart not found");
            return "redirect:/cart";
        }

        CartEntity cart = cartOptional.get();

        if (cart.getProducts().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Cart is empty");
            return "redirect:/cart";
        }

        // Create order
        OrderEntity order = new OrderEntity();
        order.setOrderDate(LocalDateTime.now());

        double total = 0.0;
        for (ProductEntity product : cart.getProducts()) {
            // Check stock for BookEntity items
            if (product instanceof BookEntity) {
                BookEntity book = (BookEntity) product;
                if (book.getCopies() <= 0) {
                    redirectAttributes.addFlashAttribute("error",
                        "'" + book.getTitle() + "' is out of stock");
                    return "redirect:/cart";
                }

                // Update stock for books
                book.setCopies(book.getCopies() - 1);
                bookRepository.save(book);
            }

            total += product.getPrice();
            order.addProduct(product);
        }

        order.setTotalAmount(total);

        // Clear cart and save
        cart.getProducts().clear();
        orderRepository.save(order);
        cartRepository.save(cart);

        // Add to model for immediate display
        model.addAttribute("order", order);
        model.addAttribute("success", "Order placed successfully!");
        return "orderDetails";
    }

    /**
     * View all orders (optional - for testing and verification)
     */
    @GetMapping("/all")
    public String viewAllOrders(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "orderList"; // You would need to create orderList.html template
    }

    /**
     * View a specific order details
     */
    @GetMapping("/details/{orderId}")
    public String viewOrderDetails(@PathVariable Long orderId, Model model) {
        Optional<OrderEntity> orderOptional = orderRepository.findById(orderId);

        if (orderOptional.isEmpty()) {
            model.addAttribute("error", "Order not found");
            return "error"; // Or redirect to an error page
        }

        model.addAttribute("order", orderOptional.get());
        return "orderDetails";
    }
}