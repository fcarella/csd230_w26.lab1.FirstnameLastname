package csd230.lab1.repositories;

import csd230.lab1.entities.BookEntity;
import csd230.lab1.entities.CartEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CartEntityRepositoryTest {

    @Autowired
    private CartEntityRepository cartEntityRepository;

    @Autowired
    private ProductEntityRepository productEntityRepository;

    @Test
    void saveCart_withProduct_shouldPersistRelationship() {

        // 1) Save the product first (avoids cascade problems)
        BookEntity book = new BookEntity("Cart Book", 10.0, 1, "Author");
        book = productEntityRepository.save(book);

        // 2) Create cart and link product
        CartEntity cart = new CartEntity();
        cart.addProduct(book);

        // 3) Save cart
        CartEntity saved = cartEntityRepository.save(cart);

        assertNotNull(saved.getId());
        assertNotNull(saved.getProducts());
        assertEquals(1, saved.getProducts().size());
    }
}
