package csd230.lab1.repositories;

import csd230.lab1.entities.TicketEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TicketRepositoryTest {

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    void ticketCrud_works() {
        TicketEntity ticket = new TicketEntity("Concert", 49.99);

        ticket = ticketRepository.save(ticket);

        assertThat(ticket.getId()).isNotNull();
        assertThat(ticketRepository.findById(ticket.getId())).isPresent();
    }
}
