package csd230.lab1.repositories;

import csd230.lab1.entities.BookEntity;
import csd230.lab1.entities.CartEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void cartManyToMany_works() {

        // ✅ CLEAN DATABASE FOR THIS TEST
        cartRepository.deleteAll();
        bookRepository.deleteAll();

        BookEntity book = new BookEntity(
                "Cart Book",
                15.99,
                5,
                "Author",
                "ISBN-CART"
        );
        book = bookRepository.save(book);

        CartEntity cart1 = new CartEntity();
        CartEntity cart2 = new CartEntity();

        cart1.addProduct(book);
        cart2.addProduct(book);

        cartRepository.save(cart1);
        cartRepository.save(cart2);

        // ✅ ASSERT ONLY WHAT THIS TEST CREATED
        assertThat(cartRepository.findAll()).hasSize(2);

        cartRepository.findAll().forEach(cart ->
                assertThat(cart.getProducts()).hasSize(1)
        );
    }
}
