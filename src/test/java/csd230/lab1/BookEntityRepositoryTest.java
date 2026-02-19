package csd230.lab1;

import csd230.lab1.entities.BookEntity;
import csd230.lab1.repositories.BookEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookEntityRepositoryTest {

    @Autowired
    private BookEntityRepository bookRepo;

    @Test
    void create_and_read_by_id() {
        BookEntity b = new BookEntity("JUnit Book", 19.99, 10, "Tester");
        b.setIsbn("978-0000000001");
        b.setDescription("Book for testing");

        BookEntity saved = bookRepo.save(b);

        BookEntity found = bookRepo.findById(saved.getId()).orElseThrow();
        assertThat(found.getTitle()).isEqualTo("JUnit Book");
        assertThat(found.getPrice()).isEqualTo(19.99);
    }

    @Test
    void update_book_title() {
        BookEntity b = new BookEntity("Old Title", 10.00, 5, "Author");
        b.setIsbn("978-0000000002");
        b.setDescription("desc");
        BookEntity saved = bookRepo.save(b);

        saved.setTitle("New Title");
        bookRepo.save(saved);

        BookEntity updated = bookRepo.findById(saved.getId()).orElseThrow();
        assertThat(updated.getTitle()).isEqualTo("New Title");
    }

    @Test
    void delete_book() {
        BookEntity b = new BookEntity("To Delete", 5.00, 2, "Author");
        b.setIsbn("978-0000000003");
        b.setDescription("desc");
        BookEntity saved = bookRepo.save(b);

        bookRepo.deleteById(saved.getId());

        assertThat(bookRepo.findById(saved.getId())).isEmpty();
    }

    @Test
    void find_by_isbn() {
        BookEntity b = new BookEntity("ISBN Book", 15.00, 3, "Author");
        b.setIsbn("978-1111111111");
        b.setDescription("desc");
        bookRepo.save(b);

        List<BookEntity> result = bookRepo.findByIsbn("978-1111111111");
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("ISBN Book");
    }
}
