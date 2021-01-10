package com.DS.demo.services;

import com.DS.demo.DTO.*;
import com.DS.demo.models.ClientEntity;
import com.DS.demo.models.TicketEntity;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public interface TicketService {
    List<TicketEntity> getAllTicket();

    TicketResponse RechercheParId(long id);

    TicketResponse createticket(TicketRequest entity);

   TicketResponse modifyTicket(long id, TicketRequest modification);

    String deleteTicketById(long id);

    public ClientRespence ClientplusFidel();

    public float revenudansperiode(LocalDate debutperiode, LocalDate finperiode);

    public LocalDate JourPlusResrve(long id);

    public TableResponse TablePlusReserver();

    public String RevenueDerniere();

    public MetResponse PlatPlusacheter(LocalDate  begin, LocalDate  end);
}
