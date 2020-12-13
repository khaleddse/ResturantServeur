package com.DS.demo.repositories;

import com.DS.demo.models.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TableRepo extends JpaRepository<TableEntity,Long> {
    public Optional<TableEntity> findByNumero(int num);
}
