package com.DS.demo.services;

import com.DS.demo.DTO.*;
import com.DS.demo.models.*;
import com.DS.demo.repositories.DessetRepo;
import com.DS.demo.repositories.EntreeRepo;
import com.DS.demo.repositories.MetRepo;
import com.DS.demo.repositories.PlatRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MetServiceImpl {
 private    MetRepo repomet;
 private DessetRepo repodessert;
 private PlatRepo repoplat;
 private EntreeRepo repoentree;
 private ModelMapper mapper = new ModelMapper();
@Autowired
    public MetServiceImpl(MetRepo repomet, DessetRepo repodessert, PlatRepo repoplat, EntreeRepo repoentree) {
        this.repomet = repomet;
        this.repodessert = repodessert;
        this.repoplat = repoplat;
        this.repoentree = repoentree;
    }


    public List<MetResponse> getAllEntities() {

        List<MetEntity> mets=repomet.findAll();
        List<MetResponse> respences=new ArrayList<>();
        for (MetEntity met:mets){
            respences.add(mapper.map(met,MetResponse.class));
        }
        return respences;
    }
    public List<PlatRespence> getAllPlat(){
        List<PlatEntity> plats=repoplat.findAll();
        List<PlatRespence> respences=new ArrayList<>();
        for (PlatEntity plat:plats){
            respences.add(mapper.map(plat,PlatRespence.class));
        }
        return respences;
}
    public List<EntreeRespence> getAllEntree(){
        List<EntreeEntity> entrees=repoentree.findAll();
        List<EntreeRespence> respences=new ArrayList<>();
        for (EntreeEntity entree:entrees){
            respences.add(mapper.map(entree,EntreeRespence.class));
        }
        return respences;
}
    public List<DessetRespence> getAllDessert(){
    List<DessertEntity> desserts=repodessert.findAll();
    List<DessetRespence> respences=new ArrayList<>();
    for (DessertEntity dessert:desserts){
        respences.add(mapper.map(dessert,DessetRespence.class));
    }
    return respences;
}

    public MetResponse RechercheMetParId(long id) {

        Optional<MetEntity> metOpt = repomet.findById(id);
        MetEntity met;
        if (metOpt.isPresent())
            met = metOpt.get();
        else
            throw new NoSuchElementException("aucun Met existe avec cette Id");
        return mapper.map(met, MetResponse.class);
    }

    public PlatRespence createPlat(PlatRequest entitydto) {
        PlatEntity entity = mapper.map(entitydto,PlatEntity.class);

        entity.setNom(entity.getNom().toUpperCase());

       MetEntity met= repoplat.save(entity);

        return mapper.map(met, PlatRespence.class);
    }
    public DessetRespence createDessert(DessertRequest entitydto) {

       DessertEntity entity = mapper.map(entitydto, DessertEntity.class);

        entity.setNom(entity.getNom().toUpperCase());

        DessertEntity met= repodessert.save(entity);

        return mapper.map(met, DessetRespence.class);
    }
    public EntreeRespence createEntree(EntreeRequest entitydto) {

        EntreeEntity entity = mapper.map(entitydto, EntreeEntity.class);

        entity.setNom(entity.getNom().toUpperCase());

        EntreeEntity  met=repoentree.save(entity);

        return mapper.map(met, EntreeRespence.class);
    }



    public MetResponse modifyMet(long id, MetRequest newEntityreq) {
        MetEntity newEntity = mapper.map(newEntityreq,  MetEntity.class);
        MetResponse oldmets= this.RechercheMetParId(id);
        MetEntity  oldmet=mapper.map(oldmets,MetEntity.class);

        if(newEntity.getPrix()!=0){
            oldmet.setPrix(newEntity.getPrix());
        }
        if(newEntity.getNom()!=null){

            oldmet.setNom(newEntity.getNom().toUpperCase());
        }
        MetEntity metDb=repomet.save(oldmet);
        return mapper.map(metDb,MetResponse.class);

    }


    public String deleteMetById(long id) {
        MetResponse met=this.RechercheMetParId(id);
        repomet.deleteById(id);
        return "Met supprimer ";
    }


    public MetResponse RechercheParNom(String nom) {
        String Nom=nom.toUpperCase();
        Optional<MetEntity> opt= repomet.findByNom(Nom);
        MetEntity met;
        if (opt.isPresent())
            met= opt.get();
        else
            throw new NoSuchElementException("Met avec cette nom n'existe pas");

        return mapper.map(met,MetResponse.class);

    }


}
