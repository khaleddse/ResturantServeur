package com.DS.demo.services;

import ch.qos.logback.core.net.server.Client;
import com.DS.demo.models.ClientEntity;
import com.DS.demo.models.MetEntity;
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
    public ClientEntity getClientById(long id) {
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
        clientRequest.setNom(clientRequest.getNom().toUpperCase());
       return repoclient.save(clientRequest);
    }

    @Override
    public ClientEntity modifyClient(long id, ClientEntity newEntity) {
        ClientEntity oldclient= this.getClientById(id);
        if(newEntity.getNom()!=null){
            oldclient.setNom(newEntity.getNom().toUpperCase());
        }
        if(newEntity.getPrenom()!=null){
            oldclient.setPrenom(newEntity.getPrenom());
        }
        if(newEntity.getCourriel()!=null){
            oldclient.setCourriel(newEntity.getCourriel());
        }
        if(newEntity.getDateDeNaissance()!=null){
            oldclient.setDateDeNaissance(newEntity.getDateDeNaissance());
        }
        if(newEntity.getTelephone()!=null){
            oldclient.setTelephone(newEntity.getTelephone());
        }
        return repoclient.save(oldclient);
    }

    @Override
    public String deleteClientById(long id) {
        ClientEntity client=this.getClientById(id);
        repoclient.deleteById(id);
        return "client supprimer";
    }

    @Override
    public ClientEntity RechercheParNom(String nom) {
        String Nom=nom.toUpperCase();
        return repoclient.findByNom(Nom).orElseThrow(()-> new NoSuchElementException("Aucun Client avec cette nom"));

    }
}
