package com.DS.demo.repositories;

import com.DS.demo.models.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<TicketEntity,Integer> {
}
