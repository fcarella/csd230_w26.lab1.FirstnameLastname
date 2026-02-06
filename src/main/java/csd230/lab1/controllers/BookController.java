package csd230.lab1.controllers;

import csd230.lab1.repositories.BookEntityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    private final BookEntityRepository bookRepository;

    public BookController(BookEntityRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public String bookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "bookList";
    }
}
