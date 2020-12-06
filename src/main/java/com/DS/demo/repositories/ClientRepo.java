package com.DS.demo.repositories;

import com.DS.demo.models.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<ClientEntity,String> {
}
