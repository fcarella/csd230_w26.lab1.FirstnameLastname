package csd230.lab1.controllers;

import csd230.lab1.entities.DiscMagEntity;
import csd230.lab1.repositories.DiscMagRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Disc Magazines REST API", description = "JSON CRUD API for DiscMag (Magazine + Disc)")
@RestController
@RequestMapping("/api/rest/discmags")
@CrossOrigin(origins = "*")
public class DiscMagRestController {

    private final DiscMagRepository discMagRepository;

    public DiscMagRestController(DiscMagRepository discMagRepository) {
        this.discMagRepository = discMagRepository;
    }

    @Operation(summary = "Get all disc magazines")
    @ApiResponse(responseCode = "200", description = "List of disc magazines returned")
    @GetMapping
    public List<DiscMagEntity> all() {
        return discMagRepository.findAll();
    }

    @Operation(summary = "Get one disc magazine by ID")
    @ApiResponse(responseCode = "200", description = "Disc magazine found and returned")
    @ApiResponse(responseCode = "404", description = "Disc magazine not found")
    @GetMapping("/{id}")
    public DiscMagEntity one(@PathVariable Long id) {
        return discMagRepository.findById(id)
                .orElseThrow(() -> new DiscMagNotFoundException(id));
    }

    @Operation(summary = "Create a new disc magazine")
    @ApiResponse(responseCode = "200", description = "Disc magazine created and returned")
    @PostMapping
    public DiscMagEntity create(@RequestBody DiscMagEntity newDiscMag) {
        newDiscMag.setId(null);
        return discMagRepository.save(newDiscMag);
    }

    @Operation(summary = "Update/Replace a disc magazine by ID")
    @ApiResponse(responseCode = "200", description = "Disc magazine updated (or created if not found)")
    @PutMapping("/{id}")
    public DiscMagEntity replace(@RequestBody DiscMagEntity newDiscMag, @PathVariable Long id) {
        return discMagRepository.findById(id)
                .map(dm -> {
                    dm.setTitle(newDiscMag.getTitle());
                    dm.setPrice(newDiscMag.getPrice());
                    dm.setCopies(newDiscMag.getCopies());
                    dm.setOrderQty(newDiscMag.getOrderQty());
                    dm.setCurrentIssue(newDiscMag.getCurrentIssue());
                    dm.setHasDisc(newDiscMag.isHasDisc());
                    return discMagRepository.save(dm);
                })
                .orElseGet(() -> {
                    newDiscMag.setId(id);
                    return discMagRepository.save(newDiscMag);
                });
    }

    @Operation(summary = "Delete a disc magazine by ID")
    @ApiResponse(responseCode = "200", description = "Disc magazine deleted")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        discMagRepository.deleteById(id);
    }
}
