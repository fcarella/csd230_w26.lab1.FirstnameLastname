package csd230.lab1.controllers;

import csd230.lab1.entities.OrderEntity;
import csd230.lab1.repositories.OrderEntityRepository;
import csd230.lab1.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final OrderEntityRepository orderRepository;

    public OrderController(OrderService orderService, OrderEntityRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @PostMapping("/cart/checkout")
    public String checkout(RedirectAttributes ra) {
        try {
            OrderEntity order = orderService.checkoutDefaultCart();
            return "redirect:/orders/" + order.getId();
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
            return "redirect:/cart";
        }
    }

    @GetMapping("/orders/{id}")
    public String orderDetails(@PathVariable Long id, Model model) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));
        model.addAttribute("order", order);
        return "orderDetails";
    }
}
