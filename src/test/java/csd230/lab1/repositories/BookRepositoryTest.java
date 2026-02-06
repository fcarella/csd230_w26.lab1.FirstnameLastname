package csd230.lab1.repositories;

import csd230.lab1.entities.BookEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void bookCrud_works() {
        BookEntity book = new BookEntity(
                "Test Book",
                19.99,
                10,
                "Test Author",
                "ISBN-123"
        );

        book = bookRepository.save(book);

        assertThat(book.getId()).isNotNull();
        assertThat(bookRepository.findById(book.getId())).isPresent();
        assertThat(bookRepository.findByIsbn("ISBN-123")).hasSize(1);
    }
}
