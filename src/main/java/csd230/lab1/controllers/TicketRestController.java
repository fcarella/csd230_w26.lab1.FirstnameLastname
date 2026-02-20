package csd230.lab1.controllers;

import csd230.lab1.entities.TicketEntity;
import csd230.lab1.repositories.TicketRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tickets REST API", description = "JSON CRUD API for Tickets")
@RestController
@RequestMapping("/api/rest/tickets")
@CrossOrigin(origins = "*")
public class TicketRestController {

    private final TicketRepository ticketRepository;

    public TicketRestController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Operation(summary = "Get all tickets")
    @ApiResponse(responseCode = "200", description = "List of tickets returned")
    @GetMapping
    public List<TicketEntity> all() {
        return ticketRepository.findAll();
    }

    @Operation(summary = "Get one ticket by ID")
    @ApiResponse(responseCode = "200", description = "Ticket found and returned")
    @ApiResponse(responseCode = "404", description = "Ticket not found")
    @GetMapping("/{id}")
    public TicketEntity one(@PathVariable Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
    }

    @Operation(summary = "Create a new ticket")
    @ApiResponse(responseCode = "200", description = "Ticket created and returned")
    @PostMapping
    public TicketEntity create(@RequestBody TicketEntity newTicket) {
        newTicket.setId(null);
        return ticketRepository.save(newTicket);
    }

    @Operation(summary = "Update/Replace a ticket by ID")
    @ApiResponse(responseCode = "200", description = "Ticket updated (or created if not found)")
    @PutMapping("/{id}")
    public TicketEntity replace(@RequestBody TicketEntity newTicket, @PathVariable Long id) {
        return ticketRepository.findById(id)
                .map(t -> {
                    t.setDescription(newTicket.getDescription());
                    t.setPrice(newTicket.getPrice());
                    return ticketRepository.save(t);
                })
                .orElseGet(() -> {
                    newTicket.setId(id);
                    return ticketRepository.save(newTicket);
                });
    }

    @Operation(summary = "Delete a ticket by ID")
    @ApiResponse(responseCode = "200", description = "Ticket deleted")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ticketRepository.deleteById(id);
    }
}
