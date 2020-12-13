package com.DS.demo.services;

import com.DS.demo.models.ClientEntity;
import com.DS.demo.models.TicketEntity;

import java.util.List;

public interface TicketService {
    List<TicketEntity> getAllTicket();

    TicketEntity getTicketById(long id);

    TicketEntity createticket(TicketEntity entity);

   TicketEntity modifyTicket(long id, TicketEntity modification);

    TicketEntity deleteTicketById(long id);


}
