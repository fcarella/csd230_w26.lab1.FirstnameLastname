package csd230.lab1.controllers;

import csd230.lab1.entities.MagazineEntity;
import csd230.lab1.repositories.MagazineRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Magazines REST API", description = "JSON CRUD API for Magazines")
@RestController
@RequestMapping("/api/rest/magazines")
@CrossOrigin(origins = "*")
public class MagazineRestController {

    private final MagazineRepository magazineRepository;

    public MagazineRestController(MagazineRepository magazineRepository) {
        this.magazineRepository = magazineRepository;
    }

    @Operation(summary = "Get all magazines")
    @ApiResponse(responseCode = "200", description = "List of magazines returned")
    @GetMapping
    public List<MagazineEntity> all() {
        return magazineRepository.findAll();
    }

    @Operation(summary = "Get one magazine by ID")
    @ApiResponse(responseCode = "200", description = "Magazine found and returned")
    @ApiResponse(responseCode = "404", description = "Magazine not found")
    @GetMapping("/{id}")
    public MagazineEntity one(@PathVariable Long id) {
        return magazineRepository.findById(id)
                .orElseThrow(() -> new MagazineNotFoundException(id));
    }

    @Operation(summary = "Create a new magazine")
    @ApiResponse(responseCode = "200", description = "Magazine created and returned")
    @PostMapping
    public MagazineEntity create(@RequestBody MagazineEntity newMagazine) {
        newMagazine.setId(null);
        return magazineRepository.save(newMagazine);
    }

    @Operation(summary = "Update/Replace a magazine by ID")
    @ApiResponse(responseCode = "200", description = "Magazine updated (or created if not found)")
    @PutMapping("/{id}")
    public MagazineEntity replace(@RequestBody MagazineEntity newMagazine, @PathVariable Long id) {
        return magazineRepository.findById(id)
                .map(mag -> {
                    mag.setTitle(newMagazine.getTitle());
                    mag.setPrice(newMagazine.getPrice());
                    mag.setCopies(newMagazine.getCopies());
                    mag.setOrderQty(newMagazine.getOrderQty());
                    mag.setCurrentIssue(newMagazine.getCurrentIssue());
                    return magazineRepository.save(mag);
                })
                .orElseGet(() -> {
                    newMagazine.setId(id);
                    return magazineRepository.save(newMagazine);
                });
    }

    @Operation(summary = "Delete a magazine by ID")
    @ApiResponse(responseCode = "200", description = "Magazine deleted")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        magazineRepository.deleteById(id);
    }
}
