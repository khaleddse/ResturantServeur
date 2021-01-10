package com.DS.demo.endpoints;

import com.DS.demo.DTO.*;
import com.DS.demo.models.ClientEntity;
import com.DS.demo.models.TicketEntity;
import com.DS.demo.services.TicketService;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
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



    //a) Pour une période donnée quel est le plat le plus acheté ?

    @GetMapping("/MeillieurPlat/{begin}/{end}")
    public MetResponse PlatPlusacheter(@PathVariable("begin")String begin, @PathVariable("end")String end){
        LocalDate datebegin=LocalDate.parse(begin);
        LocalDate datefin=LocalDate.parse(end);
        return service.PlatPlusacheter(datebegin,datefin);
    };

    //b) Quel est le client le plus fidèle au restaurant ?

    @GetMapping("/fidel")
    public ClientRespence ClientplusFidel() {
        return service.ClientplusFidel();
    }
    //c) Quelle est la table la plus réservée ?

    @GetMapping("/tablereserve")
    public TableResponse TablePlusReserver(){
        return service.TablePlusReserver();
    };

    // d) Quel est le jour de la semaine le plus réservé par un client donné ?

    @GetMapping("/client/{id}")
    public LocalDate JourPlusResrve(@PathVariable("id") long id){
        return service.JourPlusResrve(id);
    };




    //e) Retourner le revenu par jour, semaine et mois.

    @GetMapping("/RevenuJSM")
    public String RevenueDerniere(){
        return service.RevenueDerniere();
    };

    //f) Retourner le revenu pour une période donnée.

    @GetMapping("/revenu/{begin}/{end}")
    public float revenudansperiode(@PathVariable("begin")String debutperiode,@PathVariable("end") String finperiode){
        LocalDate datebegin=LocalDate.parse(debutperiode);
        LocalDate datefin=LocalDate.parse(finperiode);
        return service.revenudansperiode(datebegin,datefin);
    }

}