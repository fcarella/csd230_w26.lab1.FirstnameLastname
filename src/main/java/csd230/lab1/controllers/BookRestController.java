package csd230.lab1.controllers;

import csd230.lab1.entities.BookEntity;
import csd230.lab1.repositories.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Books REST API", description = "JSON CRUD API for Books")
@RestController
@RequestMapping("/api/rest/books")
@CrossOrigin(origins = "*")
public class BookRestController {

    private final BookRepository bookRepository;

    public BookRestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Operation(summary = "Get all books")
    @ApiResponse(responseCode = "200", description = "List of books returned")
    @GetMapping
    public List<BookEntity> all() {
        return bookRepository.findAll();
    }

    @Operation(summary = "Get one book by ID")
    @ApiResponse(responseCode = "200", description = "Book found and returned")
    @ApiResponse(responseCode = "404", description = "Book not found")
    @GetMapping("/{id}")
    public BookEntity one(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Operation(summary = "Create a new book")
    @ApiResponse(responseCode = "200", description = "Book created and returned")
    @PostMapping
    public BookEntity create(@RequestBody BookEntity newBook) {
        newBook.setId(null); // ensure new row
        return bookRepository.save(newBook);
    }

    @Operation(summary = "Update/Replace a book by ID")
    @ApiResponse(responseCode = "200", description = "Book updated (or created if not found)")
    @PutMapping("/{id}")
    public BookEntity replace(@RequestBody BookEntity newBook, @PathVariable Long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(newBook.getTitle());
                    book.setAuthor(newBook.getAuthor());
                    book.setIsbn(newBook.getIsbn());
                    book.setPrice(newBook.getPrice());
                    book.setCopies(newBook.getCopies());
                    return bookRepository.save(book);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return bookRepository.save(newBook);
                });
    }

    @Operation(summary = "Delete a book by ID")
    @ApiResponse(responseCode = "200", description = "Book deleted")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}
