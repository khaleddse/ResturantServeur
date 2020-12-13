package com.DS.demo.repositories;

import com.DS.demo.models.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepo extends JpaRepository<TableEntity,Long> {
}
