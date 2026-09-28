package csd230.lab1.controllers;

import csd230.lab1.entities.CartEntity;
import csd230.lab1.entities.SmartPhoneEntity;
import csd230.lab1.repositories.CartEntityRepository;
import csd230.lab1.repositories.SmartPhoneEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC Controller handling web requests for SmartPhone entity operations.
 */
@Controller
@RequestMapping("/smartphones")
public class SmartPhoneController {

    @Autowired
    private SmartPhoneEntityRepository smartPhoneRepository;

    @Autowired
    private CartEntityRepository cartRepository;

    /**
     * Displays all smartphones in the catalog.
     *
     * @param model Spring UI Model
     * @return Logical view name for the smartphone list template
     */
    @GetMapping
    public String getAllSmartPhones(Model model) {
        model.addAttribute("smartphones", smartPhoneRepository.findAll());
        return "smartPhoneList";
    }

    /**
     * Displays details for a specific smartphone by ID.
     *
     * @param id    SmartPhone primary key ID
     * @param model Spring UI Model
     * @return Logical view name for smartphone details template
     */
    @GetMapping("/{id}")
    public String getSmartPhoneById(@PathVariable Long id, Model model) {
        SmartPhoneEntity smartPhone = smartPhoneRepository.findById(id).orElse(null);
        model.addAttribute("smartphone", smartPhone);
        return "smartPhoneDetails";
    }

    /**
     * Renders form to add a new smartphone.
     *
     * @param model Spring UI Model
     * @return Logical view name for add smartphone template
     */
    @GetMapping("/add")
    public String addSmartPhoneForm(Model model) {
        model.addAttribute("smartphone", new SmartPhoneEntity());
        return "addSmartPhone";
    }

    /**
     * Processes form submission to create a new smartphone.
     *
     * @param smartPhone SmartPhone entity populated from form inputs
     * @return Redirect URL to the smartphones list
     */
    @PostMapping("/add")
    public String addSmartPhone(@ModelAttribute SmartPhoneEntity smartPhone) {
        smartPhoneRepository.save(smartPhone);
        return "redirect:/smartphones";
    }

    /**
     * Renders form to edit an existing smartphone.
     *
     * @param id    SmartPhone primary key ID
     * @param model Spring UI Model
     * @return Logical view name for edit smartphone template
     */
    @GetMapping("/edit/{id}")
    public String editSmartPhoneForm(@PathVariable Long id, Model model) {
        SmartPhoneEntity smartPhone = smartPhoneRepository.findById(id).orElse(null);
        model.addAttribute("smartphone", smartPhone);
        return "editSmartPhone";
    }

    /**
     * Processes form submission to update an existing smartphone.
     *
     * @param id                 SmartPhone primary key ID
     * @param updatedSmartPhone SmartPhone entity containing updated values
     * @return Redirect URL to the smartphones list
     */
    @PostMapping("/edit/{id}")
    public String editSmartPhone(@PathVariable Long id, @ModelAttribute SmartPhoneEntity updatedSmartPhone) {
        SmartPhoneEntity existingSmartPhone = smartPhoneRepository.findById(id).orElse(null);
        if (existingSmartPhone != null) {
            existingSmartPhone.setBrand(updatedSmartPhone.getBrand());
            existingSmartPhone.setPrice(updatedSmartPhone.getPrice());
            existingSmartPhone.setDescription(updatedSmartPhone.getDescription());
            existingSmartPhone.setScreenSizeInches(updatedSmartPhone.getScreenSizeInches());
            smartPhoneRepository.save(existingSmartPhone);
        }
        return "redirect:/smartphones";
    }

    /**
     * Handles smartphone deletion, removing it from active carts prior to database deletion to avoid foreign key violations.
     *
     * @param id SmartPhone primary key ID
     * @return Redirect URL to the smartphones list
     */
    @GetMapping("/delete/{id}")
    public String deleteSmartPhone(@PathVariable Long id) {
        SmartPhoneEntity smartPhone = smartPhoneRepository.findById(id).orElse(null);
        if (smartPhone != null) {
            for (CartEntity cart : smartPhone.getCarts()) {
                cart.getProducts().remove(smartPhone);
                cartRepository.save(cart);
            }
            smartPhoneRepository.deleteById(id);
        }
        return "redirect:/smartphones";
    }
}