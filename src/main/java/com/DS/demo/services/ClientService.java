package com.DS.demo.services;

import com.DS.demo.models.ClientEntity;

import java.util.List;

public interface ClientService {


        List<ClientEntity> getAllEntities();

       ClientEntity getEntityById(String id);

        ClientEntity createClient(ClientEntity entity);

        ClientEntity modifyPerson(long id, ClientEntity newEntity);

        ClientEntity deletePersonById(long id);

        public ClientEntity getByName(String name);
}
