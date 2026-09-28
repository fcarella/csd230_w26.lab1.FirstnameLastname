package csd230.lab1.controllers;

import csd230.lab1.entities.CartEntity;
import csd230.lab1.entities.LaptopEntity;
import csd230.lab1.repositories.CartEntityRepository;
import csd230.lab1.repositories.LaptopEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Spring MVC Controller handling web requests for Laptop entity operations.
 */
@Controller
@RequestMapping("/laptops")
public class LaptopController {

    @Autowired
    private LaptopEntityRepository laptopRepository;

    @Autowired
    private CartEntityRepository cartRepository;

    /**
     * Displays all laptops in the catalog.
     *
     * @param model Spring UI Model
     * @return Logical view name for the laptop list template
     */
    @GetMapping
    public String getAllLaptops(Model model) {
        model.addAttribute("laptops", laptopRepository.findAll());
        return "laptopList";
    }

    /**
     * Displays details for a specific laptop by ID.
     *
     * @param id    Laptop primary key ID
     * @param model Spring UI Model
     * @return Logical view name for laptop details template
     */
    @GetMapping("/{id}")
    public String getLaptopById(@PathVariable Long id, Model model) {
        LaptopEntity laptop = laptopRepository.findById(id).orElse(null);
        model.addAttribute("laptop", laptop);
        return "laptopDetails";
    }

    /**
     * Renders form to add a new laptop.
     *
     * @param model Spring UI Model
     * @return Logical view name for add laptop template
     */
    @GetMapping("/add")
    public String addLaptopForm(Model model) {
        model.addAttribute("laptop", new LaptopEntity());
        return "addLaptop";
    }

    /**
     * Processes form submission to create a new laptop.
     *
     * @param laptop Laptop entity populated from form inputs
     * @return Redirect URL to the laptops list
     */
    @PostMapping("/add")
    public String addLaptop(@ModelAttribute LaptopEntity laptop) {
        laptopRepository.save(laptop);
        return "redirect:/laptops";
    }

    /**
     * Renders form to edit an existing laptop.
     *
     * @param id    Laptop primary key ID
     * @param model Spring UI Model
     * @return Logical view name for edit laptop template
     */
    @GetMapping("/edit/{id}")
    public String editLaptopForm(@PathVariable Long id, Model model) {
        LaptopEntity laptop = laptopRepository.findById(id).orElse(null);
        model.addAttribute("laptop", laptop);
        return "editLaptop";
    }

    /**
     * Processes form submission to update an existing laptop.
     *
     * @param id            Laptop primary key ID
     * @param updatedLaptop Laptop entity containing updated values
     * @return Redirect URL to the laptops list
     */
    @PostMapping("/edit/{id}")
    public String editLaptop(@PathVariable Long id, @ModelAttribute LaptopEntity updatedLaptop) {
        LaptopEntity existingLaptop = laptopRepository.findById(id).orElse(null);
        if (existingLaptop != null) {
            existingLaptop.setBrand(updatedLaptop.getBrand());
            existingLaptop.setPrice(updatedLaptop.getPrice());
            existingLaptop.setDescription(updatedLaptop.getDescription());
            existingLaptop.setRamGb(updatedLaptop.getRamGb());
            laptopRepository.save(existingLaptop);
        }
        return "redirect:/laptops";
    }

    /**
     * Handles laptop deletion, removing it from active carts prior to database deletion to avoid foreign key violations.
     *
     * @param id Laptop primary key ID
     * @return Redirect URL to the laptops list
     */
    @GetMapping("/delete/{id}")
    public String deleteLaptop(@PathVariable Long id) {
        LaptopEntity laptop = laptopRepository.findById(id).orElse(null);
        if (laptop != null) {
            for (CartEntity cart : laptop.getCarts()) {
                cart.getProducts().remove(laptop);
                cartRepository.save(cart);
            }
            laptopRepository.deleteById(id);
        }
        return "redirect:/laptops";
    }
}