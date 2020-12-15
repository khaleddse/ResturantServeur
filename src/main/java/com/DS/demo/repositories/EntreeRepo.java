package com.DS.demo.repositories;

import com.DS.demo.models.EntreeEntity;
import com.DS.demo.models.MetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntreeRepo extends JpaRepository<EntreeEntity,Long> {
}
