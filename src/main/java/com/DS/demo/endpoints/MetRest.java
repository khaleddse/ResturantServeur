package com.DS.demo.endpoints;

import com.DS.demo.models.MetEntity;
import com.DS.demo.services.MetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/met")
public class MetRest {
   private MetService service;
@Autowired
    public MetRest(MetService service) {
        super();
        this.service = service;
    }
    @GetMapping
    List<MetEntity> getAllEntities(){
    return service.getAllEntities();
    }
@GetMapping("{id}")
    MetEntity RechercheMetParId(@PathVariable("id")long id){
        return service.RechercheMetParId(id);
    }
@PostMapping("/add")
    MetEntity createMet(@RequestBody MetEntity entity){
    return service.createMet(entity);
}
@PostMapping("/update/{id}")
    MetEntity modifyMet(@PathVariable("id") long id, @RequestBody MetEntity newEntity){
    return service.modifyMet(id,newEntity);
}
@DeleteMapping("/delete/{id}")
    String deleteMetById(@PathVariable("id") long id){
   return service.deleteMetById(id);
}
@GetMapping("/recherche/{nom}")
    MetEntity RechercheParNom (@PathVariable("nom") String nom){
    return service.RechercheParNom(nom);
}
    @GetMapping("/type/{type}")
    MetEntity RechercheParType(@PathVariable("type") String type){
    return service.RechercheParType(type);
    }
}
