package com.DS.demo.repositories;

import com.DS.demo.models.MetEntity;
import com.DS.demo.models.PlatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatRepo extends JpaRepository<PlatEntity,Long> {
}
