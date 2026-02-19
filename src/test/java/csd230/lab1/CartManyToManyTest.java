package csd230.lab1.repositories;

import csd230.lab1.entities.BookEntity;
import csd230.lab1.entities.CartEntity;
import csd230.lab1.entities.ProductEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CartManyToManyTest {

    @Autowired private CartEntityRepository cartRepo;
    @Autowired private ProductEntityRepository productRepo;
    @Autowired private BookEntityRepository bookRepo;

    @Test
    void same_product_can_be_in_multiple_carts() {
        BookEntity b = new BookEntity("Shared Book", 12.00, 10, "Author");
        b.setIsbn("978-2222222222");
        b.setDescription("shared");
        BookEntity savedBook = bookRepo.save(b);

        CartEntity c1 = new CartEntity();
        CartEntity c2 = new CartEntity();

        c1.addProduct(savedBook);
        c2.addProduct(savedBook);

        cartRepo.save(c1);
        cartRepo.save(c2);

        CartEntity c1db = cartRepo.findById(c1.getId()).orElseThrow();
        CartEntity c2db = cartRepo.findById(c2.getId()).orElseThrow();

        assertThat(c1db.getProducts()).hasSize(1);
        assertThat(c2db.getProducts()).hasSize(1);

        ProductEntity p1 = c1db.getProducts().iterator().next();
        ProductEntity p2 = c2db.getProducts().iterator().next();

        assertThat(p1.getId()).isEqualTo(savedBook.getId());
        assertThat(p2.getId()).isEqualTo(savedBook.getId());
    }
}
