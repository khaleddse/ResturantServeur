package com.DS.demo.endpoints;

import com.DS.demo.DTO.ClientRequest;
import com.DS.demo.DTO.ClientRespence;
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
    ClientRespence getClientById(@PathVariable("id") long id){
        return service.getClientById(id);
}
@PostMapping("/add")
ClientRespence createClient(@RequestBody ClientRequest entity){
        return service.createClient(entity);
}
@PostMapping("/update/{id}")
    ClientRespence modifyClient(@PathVariable("id") long id, @RequestBody ClientRequest  newEntity){
        return service.modifyClient(id,newEntity);
}
@DeleteMapping("/delete/{id}")
    String deleteClientById(@PathVariable("id") long id){
        return service.deleteClientById(id);
}
@GetMapping("/recherche/{nom}")
    ClientRespence  RechercheParNom(@PathVariable("nom") String nom){
        return service.RechercheParNom(nom);
}

}
