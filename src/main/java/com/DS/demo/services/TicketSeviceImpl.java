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
    public TicketEntity getTicketById(long id) {
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
        return null;
    }

    @Override
    public TicketEntity modifyTicket(long id, TicketEntity modification) {
        return null;
    }

    @Override
    public TicketEntity deleteTicketById(long id) {
        TicketEntity ticket=this.getTicketById(id);
        repoticket.deleteById(id);
         return ticket;
    }
}
