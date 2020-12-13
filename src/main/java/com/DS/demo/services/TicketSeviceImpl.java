package com.DS.demo.services;


import com.DS.demo.models.TicketEntity;
import com.DS.demo.repositories.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class TicketSeviceImpl implements TicketService{

    TicketRepo repoticket;
@Autowired
    public TicketSeviceImpl(TicketRepo repoticket) {
        this.repoticket = repoticket;
    }

    @Override
    public List<TicketEntity> getAllTicket() {
        return repoticket.findAll();
    }

    @Override
    public TicketEntity RechercheParId(long id) {
        Optional<TicketEntity> ticketOpt=repoticket.findById(id);
        TicketEntity ticket;
        if(ticketOpt.isPresent())
            ticket=ticketOpt.get();
        else
            throw new NoSuchElementException("Aucun ticket avec cette id");
        return ticket;
    }

    @Override
    public TicketEntity createticket(TicketEntity entity) {
    return repoticket.save(entity);
    }

    @Override
    public TicketEntity modifyTicket(long id, TicketEntity modification) {
    TicketEntity oldticket=this.RechercheParId(id);
    if(modification.getNumero()!=null){
        oldticket.setNumero(modification.getNumero());
    }
    if(modification.getAddition()!=0){
        oldticket.setAddition(modification.getAddition());
    }
    if(modification.getDate()!=null){
        oldticket.setDate(modification.getDate());
    }
    if(modification.getNbCouvert()!=null){
        oldticket.setNbCouvert(modification.getNbCouvert());
    }
    return oldticket;
    }

    @Override
    public String deleteTicketById(long id) {
        TicketEntity ticket=this.RechercheParId(id);
        repoticket.deleteById(id);
         return "ticket Supprimer";
    }
}
