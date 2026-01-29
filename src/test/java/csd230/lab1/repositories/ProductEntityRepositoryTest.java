package csd230.lab1.repositories;

import csd230.lab1.entities.BookEntity;
import csd230.lab1.entities.ProductEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductEntityRepositoryTest {

    @Autowired
    private ProductEntityRepository productEntityRepository;

    @Test
    void saveBook_andFindAll_shouldReturnAtLeastOneProduct() {

        BookEntity book = new BookEntity("JUnit Book", 19.99, 5, "Test Author");
        productEntityRepository.save(book);

        List<ProductEntity> all = productEntityRepository.findAll();
        assertNotNull(all);
        assertFalse(all.isEmpty());
    }
}
