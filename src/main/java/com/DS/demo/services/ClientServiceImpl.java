package com.DS.demo.services;

import com.DS.demo.models.ClientEntity;
import com.DS.demo.repositories.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepo repoclient;

    @Autowired
    public ClientServiceImpl(ClientRepo repoclient) {
        super();
        this.repoclient = repoclient;
    }

    @Override
    public List<ClientEntity> getAllEntities() {
        return  repoclient.findAll();
    }

    @Override
    public ClientEntity getEntityById(String id) {
        Optional<ClientEntity> clientOp= repoclient.findById(id);
        ClientEntity  client;
        if(clientOp.isPresent()){
            client=clientOp.get();
        }else {
            throw new NoSuchElementException("Client avec cette id n'existe pas");
        }
        return client;
    }

    @Override
    public ClientEntity createClient(ClientEntity clientRequest) {

       return repoclient.save(clientRequest);
    }

    @Override
    public ClientEntity modifyPerson(long id, ClientEntity newEntity) {
        return null;
    }

    @Override
    public ClientEntity deletePersonById(long id) {
        return null;
    }

    @Override
    public ClientEntity getByName(String name) {
        return null;
    }
}
