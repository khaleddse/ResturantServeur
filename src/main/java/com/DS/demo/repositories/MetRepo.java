package com.DS.demo.repositories;

import com.DS.demo.models.MetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetRepo extends JpaRepository<MetEntity,String> {
}
