package com.DS.demo.repositories;

import com.DS.demo.models.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;

public interface TicketRepo extends JpaRepository<TicketEntity,Long> {
    Optional<TicketEntity> findByDate(Instant date);
}
