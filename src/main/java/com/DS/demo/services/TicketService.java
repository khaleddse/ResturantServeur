package com.DS.demo.services;

import com.DS.demo.DTO.MetResponse;
import com.DS.demo.DTO.TableResponse;
import com.DS.demo.DTO.TicketRequest;
import com.DS.demo.DTO.TicketResponse;
import com.DS.demo.models.ClientEntity;
import com.DS.demo.models.TicketEntity;

import java.time.Instant;
import java.util.List;

public interface TicketService {
    List<TicketEntity> getAllTicket();

    TicketResponse RechercheParId(long id);

    TicketResponse createticket(TicketRequest entity);

   TicketResponse modifyTicket(long id, TicketRequest modification);

    String deleteTicketById(long id);

    public ClientEntity  ClientplusFidel(Instant debutperiode, Instant finperiode);

    public float revenudansperiode(Instant debutperiode, Instant finperiode);

    public Instant JourPlusResrve(long id);

    public TableResponse TablePlusReserver();

    public String RevenueDerniere();

    public MetResponse PlatPlusacheter(Instant begin, Instant end);
}
