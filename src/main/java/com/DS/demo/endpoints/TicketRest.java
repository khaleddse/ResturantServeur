package com.DS.demo.endpoints;

import com.DS.demo.DTO.TicketRequest;
import com.DS.demo.DTO.TicketResponse;
import com.DS.demo.models.ClientEntity;
import com.DS.demo.models.TicketEntity;
import com.DS.demo.services.TicketService;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketRest {
    private TicketService service;

    public TicketRest(TicketService service) {
        super();
        this.service = service;
    }

    @GetMapping
    List<TicketEntity> getAllTicket() {
        return service.getAllTicket();
    }

    @GetMapping("/recherche/{id}")
    TicketResponse RechercheParId(@PathVariable("id") long id) {
        return service.RechercheParId(id);
    }

    @PostMapping("/add")
    TicketResponse createticket(@RequestBody TicketRequest entity) {
        return service.createticket(entity);
    }

    @PostMapping("/update/{id}")
    TicketResponse modifyTicket(@PathVariable("id") long id, @RequestBody TicketRequest modification) {
        return service.modifyTicket(id, modification);
    }

    @DeleteMapping("/delete/{id}")
    String deleteTicketById(@PathVariable("id") long id) {
        return service.deleteTicketById(id);
    }

    @GetMapping("/fidel/{begin}/{end}")
    public ClientEntity ClientplusFidel(@PathVariable("begin") Instant debutperiode, @PathVariable("end") Instant finperiode) {
        return service.ClientplusFidel(debutperiode, finperiode);
    }
    @GetMapping("/revenu/{begin}/{end}")
    public float revenudansperiode(@PathVariable("begin")Instant debutperiode,@PathVariable("end") Instant finperiode){
        return service.revenudansperiode(debutperiode,finperiode);
    }
    @GetMapping("/client/{id}")
    public Instant JourPlusResrve(@PathVariable("id") long id){
        return service.JourPlusResrve(id);
    };

}