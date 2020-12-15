package com.DS.demo.endpoints;

import com.DS.demo.DTO.*;
import com.DS.demo.models.DessertEntity;
import com.DS.demo.models.EntreeEntity;
import com.DS.demo.models.MetEntity;
import com.DS.demo.models.PlatEntity;
import com.DS.demo.services.MetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/met")
public class MetRest {
   private MetServiceImpl service;
@Autowired
    public MetRest(MetServiceImpl service) {
        super();
        this.service = service;
    }
    @GetMapping
    List<MetResponse> getAllEntities(){
    return service.getAllEntities();
    }
    @GetMapping("/plat")
    List<PlatRespence> getAllPlat(){
        return service.getAllPlat();
    }
    @GetMapping("/dessert")
    List<DessetRespence> getAllDessert(){
        return service.getAllDessert();
    }
    @GetMapping("/entree")
    List<EntreeRespence> getAllEntree(){
        return service.getAllEntree();
    }

@GetMapping("{id}")
    MetResponse RechercheMetParId(@PathVariable("id")long id){
        return service.RechercheMetParId(id);
    }
@PostMapping("/add/dessert")
DessetRespence createDessert(@RequestBody DessertRequest entity) {
    return service.createDessert(entity);
}
    @PostMapping("/add/plat")
    PlatRespence createPlat(@RequestBody PlatRequest entity) {
        return service.createPlat(entity);
    }
    @PostMapping("/add/entree")
    EntreeRespence createEntree(@RequestBody EntreeRequest entity) {
        return service.createEntree(entity);
    }

@PostMapping("/update/{id}")
MetResponse modifyMet(@PathVariable("id") long id, @RequestBody MetRequest newEntity){
    return service.modifyMet(id,newEntity);
}
@DeleteMapping("/delete/{id}")
    String deleteMetById(@PathVariable("id") long id){
   return service.deleteMetById(id);
}
@GetMapping("/recherche/{nom}")
MetResponse RechercheParNom (@PathVariable("nom") String nom){
    return service.RechercheParNom(nom);
}

}
