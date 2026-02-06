package csd230.lab1.services;

import csd230.lab1.entities.CartEntity;
import csd230.lab1.entities.OrderEntity;
import csd230.lab1.entities.ProductEntity;
import csd230.lab1.repositories.CartEntityRepository;
import csd230.lab1.repositories.OrderEntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderEntityRepository orderRepository;
    private final CartEntityRepository cartRepository;

    public OrderService(OrderEntityRepository orderRepository, CartEntityRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    @Transactional
    public OrderEntity checkoutDefaultCart() {

        // 1) Load cart id=1
        CartEntity cart = cartRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Cart not found (id=1)."));

        if (cart.getProducts() == null || cart.getProducts().isEmpty()) {
            throw new RuntimeException("Cart is empty. Add products before checkout.");
        }

        // 2) Create and save order
        OrderEntity order = new OrderEntity();
        // OPTIONAL: if your OrderEntity has a relationship later, you’ll set it here

        OrderEntity saved = orderRepository.save(order);

        // 3) Console proof (good for screenshot)
        System.out.println("=== CHECKOUT SUCCESS ===");
        System.out.println("Order ID: " + saved.getId());
        System.out.println("Items SOLD (calling sellItem):");
        for (ProductEntity p : cart.getProducts()) {
            p.sellItem(); // uses your SaleableItem implementation
        }

        return saved;
    }
}
