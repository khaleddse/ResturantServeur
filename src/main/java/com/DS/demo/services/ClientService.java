package com.DS.demo.services;

import com.DS.demo.models.ClientEntity;

import java.util.List;

public interface ClientService {


        List<ClientEntity> getAllEntities();

       ClientEntity getClientById(String id);

        ClientEntity createClient(ClientEntity entity);

        ClientEntity modifyClient(long id, ClientEntity newEntity);

        ClientEntity deleteClientById(long id);

        public ClientEntity getByName(String name);
}
