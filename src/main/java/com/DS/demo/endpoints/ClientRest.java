package com.DS.demo.endpoints;

import com.DS.demo.models.ClientEntity;
import com.DS.demo.services.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientRest {
    private ClientService service;

    public ClientRest(ClientService service) {
        super();
        this.service = service;
    }
    @GetMapping
    List<ClientEntity> getAllEntities() {
        return service.getAllEntities();
    }

@GetMapping("/{id}")
    ClientEntity getClientById(@PathVariable("id") long id){
        return service.getClientById(id);
}
@PostMapping("/add")
    ClientEntity createClient(@RequestBody ClientEntity entity){
        return service.createClient(entity);
}
@PostMapping("/update/{id}")
    ClientEntity modifyClient(@PathVariable("id") long id, @RequestBody ClientEntity newEntity){
        return service.modifyClient(id,newEntity);
}
@DeleteMapping("/delete/{id}")
    String deleteClientById(@PathVariable("id") long id){
        return service.deleteClientById(id);
}
@GetMapping("/recherche/{nom}")
     ClientEntity RechercheParNom(@PathVariable("nom") String nom){
        return service.RechercheParNom(nom);
}

}
