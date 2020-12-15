package com.DS.demo.services;

import ch.qos.logback.core.net.server.Client;
import com.DS.demo.DTO.ClientRequest;
import com.DS.demo.DTO.ClientRespence;
import com.DS.demo.models.ClientEntity;
import com.DS.demo.models.MetEntity;
import com.DS.demo.repositories.ClientRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepo repoclient;
    private ModelMapper mapper = new ModelMapper();

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
    public ClientRespence getClientById(long id) {
        Optional<ClientEntity> clientOp= repoclient.findById(id);
        ClientEntity  client;
        if(clientOp.isPresent()){
            client=clientOp.get();
        }else {
            throw new NoSuchElementException("Client avec cette id n'existe pas");
        }
        return mapper.map(client, ClientRespence.class);

    }

    @Override
    public ClientRespence createClient(ClientRequest clientRequestDto) {
        ClientEntity clientRequest = mapper.map(clientRequestDto, ClientEntity.class);
        clientRequest.setNom(clientRequest.getNom().toUpperCase());
        ClientEntity client =repoclient.save(clientRequest);
        return mapper.map(client, ClientRespence.class);

    }

    @Override
    public ClientRespence modifyClient(long id, ClientRequest  newEntityreq) {//
        ClientEntity newEntity = mapper.map(newEntityreq, ClientEntity.class);

        ClientRespence oldclientres= this.getClientById(id);
        ClientEntity oldclient=mapper.map(oldclientres,ClientEntity.class);

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
        ClientEntity clientDb=repoclient.save(oldclient);
        return mapper.map(clientDb,ClientRespence.class);
    }

    @Override
    public String deleteClientById(long id) {
        ClientRespence client=this.getClientById(id);
        repoclient.deleteById(id);
        return "client supprimer";
    }

    @Override
    public ClientRespence RechercheParNom(String nom) {
        String Nom=nom.toUpperCase();
         Optional<ClientEntity> opt= repoclient.findByNom(Nom);
         ClientEntity client;
         if (opt.isPresent())
             client= opt.get();
         else
             throw new NoSuchElementException("Aucun Client avec cette nom");

        return mapper.map(client,ClientRespence.class);

    }
}
