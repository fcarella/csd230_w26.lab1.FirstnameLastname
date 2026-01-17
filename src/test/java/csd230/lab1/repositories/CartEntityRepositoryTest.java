package csd230.lab1.repositories;

import csd230.lab1.entities.BookEntity;
import csd230.lab1.entities.CartEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;



import static org.junit.jupiter.api.Assertions.*;

@csd230.lab1.repositories.DataJpaTest
public class CartEntityRepositoryTest {

    @Autowired
    private CartEntityRepository cartEntityRepository;

    @Test
    void saveCart_withProduct_shouldPersistRelationship() {
        CartEntity cart = new CartEntity();

        BookEntity book = new BookEntity("Cart Book", 10.0, 1, "Author");
        cart.addProduct(book);

        CartEntity saved = cartEntityRepository.save(cart);

        assertNotNull(saved.getId());
        assertNotNull(saved.getProducts());
        assertEquals(1, saved.getProducts().size());
    }
}
