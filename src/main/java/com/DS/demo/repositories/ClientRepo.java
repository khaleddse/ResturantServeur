package com.DS.demo.repositories;

import com.DS.demo.models.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepo extends JpaRepository<ClientEntity,Long> {
    Optional<ClientEntity> findByNom(String nom);
}
