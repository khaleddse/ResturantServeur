package com.DS.demo.services;

import com.DS.demo.models.MetEntity;
import com.DS.demo.models.TableEntity;
import com.DS.demo.repositories.MetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MetServiceImpl implements MetService{
 private    MetRepo repomet;
@Autowired
    public MetServiceImpl(MetRepo repomet) {
        super();
        this.repomet = repomet;
    }

    @Override
    public List<MetEntity> getAllEntities() {
        return repomet.findAll();
    }

    @Override
    public MetEntity RechercheMetParId(long id) {
        Optional<MetEntity> metOpt = repomet.findById(id);
        MetEntity met;
        if (metOpt.isPresent())
            met = metOpt.get();
        else
            throw new NoSuchElementException("aucun Met existe avec cette Id");
        return met;
    }

    @Override
    public MetEntity createMet(MetEntity entity) {

    entity.setNom(entity.getNom().toUpperCase());
    entity.setType(entity.getType().toUpperCase());
        return repomet.save(entity);
    }

    @Override
    public MetEntity modifyMet(long id, MetEntity newEntity) {
        MetEntity oldmet= this.RechercheMetParId(id);
        if(newEntity.getPrix()!=0){
            oldmet.setPrix(newEntity.getPrix());
        }
        if(newEntity.getNom()!=null){

            oldmet.setNom(newEntity.getNom().toUpperCase());
        }
        if(newEntity.getType()!=null){
            oldmet.setType(newEntity.getType().toUpperCase());
        }

        return repomet.save(oldmet);
    }

    @Override
    public String deleteMetById(long id) {
        MetEntity met=this.RechercheMetParId(id);
        repomet.deleteById(id);
        return "Met supprimer ";
    }

    @Override
    public MetEntity RechercheParNom(String nom) {
    String NOM=nom.toUpperCase();
       return repomet.findByNom(NOM).orElseThrow(()-> new NoSuchElementException("Met avec cette nom n'existe pas"));
    }

    @Override
    public MetEntity RechercheParType(String type) {
    String TYPE=type.toUpperCase();
        return repomet.findByType(TYPE).orElseThrow(()-> new NoSuchElementException("Met avec cette type n'existe pas"));
    }
}
