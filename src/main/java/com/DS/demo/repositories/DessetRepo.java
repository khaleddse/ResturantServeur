package com.DS.demo.repositories;

import com.DS.demo.models.DessertEntity;
import com.DS.demo.models.MetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DessetRepo extends JpaRepository<DessertEntity,Long> {
}
