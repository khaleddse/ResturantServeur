package com.DS.demo.repositories;

import com.DS.demo.models.MetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MetRepo extends JpaRepository<MetEntity,Long> {
    public Optional<MetEntity> findByNom(String nom);


}
