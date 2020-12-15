package com.DS.demo.services;

import com.DS.demo.DTO.ClientRequest;
import com.DS.demo.DTO.ClientRespence;
import com.DS.demo.models.ClientEntity;

import java.util.List;

public interface ClientService {


        List<ClientEntity> getAllEntities();

    public ClientRespence getClientById(long id);

    ClientRespence createClient(ClientRequest entity);

    ClientRespence modifyClient(long id, ClientRequest  newEntity);

        String deleteClientById(long id);

        public ClientRespence RechercheParNom(String nom);
}
