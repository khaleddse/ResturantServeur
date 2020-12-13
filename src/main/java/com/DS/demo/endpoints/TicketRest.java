package com.DS.demo.endpoints;

import com.DS.demo.models.TicketEntity;
import com.DS.demo.services.TicketService;
import org.springframework.web.bind.annotation.*;

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
    List<TicketEntity> getAllTicket(){
        return service.getAllTicket();
    }
@GetMapping("/recherche/{id}")
    TicketEntity RechercheParId(@PathVariable("id") long id){
      return   service.RechercheParId(id);
}
@PostMapping("/add")
    TicketEntity createticket(@RequestBody TicketEntity entity){
        return service.createticket(entity);
}
@PostMapping("/update/{id}")
    TicketEntity modifyTicket(@PathVariable("id") long id, @RequestBody TicketEntity modification){
        return service.modifyTicket(id,modification);
}
@DeleteMapping("/delete/{id}")
    String deleteTicketById(@PathVariable("id") long id){
        return service.deleteTicketById(id);
}
}
