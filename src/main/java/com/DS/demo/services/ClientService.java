package com.DS.demo.services;

import com.DS.demo.models.ClientEntity;

import java.util.List;

public interface ClientService {


        List<ClientEntity> getAllEntities();

       ClientEntity getClientById(long id);

        ClientEntity createClient(ClientEntity entity);

        ClientEntity modifyClient(long id, ClientEntity newEntity);

        String deleteClientById(long id);

        public ClientEntity RechercheParNom(String nom);
}
