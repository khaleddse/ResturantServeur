package com.DS.demo.services;

import com.DS.demo.models.ClientEntity;
import com.DS.demo.models.MetEntity;

import java.util.List;

public interface MetService {
    List<MetEntity> getAllEntities();

    MetEntity RechercheMetParId(long id);

    MetEntity createMet(MetEntity entity);

    MetEntity modifyMet(long id, MetEntity newEntity);

    String deleteMetById(long id);

    MetEntity RechercheParNom (String nom);

    MetEntity RechercheParType(String type);
}
