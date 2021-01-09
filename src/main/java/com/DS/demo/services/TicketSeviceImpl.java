package com.DS.demo.services;


import com.DS.demo.DTO.TicketRequest;
import com.DS.demo.DTO.TicketResponse;
import com.DS.demo.models.ClientEntity;
import com.DS.demo.models.MetEntity;
import com.DS.demo.models.TicketEntity;
import com.DS.demo.repositories.ClientRepo;
import com.DS.demo.repositories.MetRepo;
import com.DS.demo.repositories.TicketRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TicketSeviceImpl implements TicketService{

    TicketRepo repoticket;
    ClientRepo repoclient;
    MetRepo repomet;

    private ModelMapper mapper = new ModelMapper();

    @Autowired
    public TicketSeviceImpl(TicketRepo repoticket, ClientRepo repoclient, MetRepo repomet) {
        super();
        this.repoticket = repoticket;
        this.repoclient = repoclient;
        this.repomet = repomet;

    }









    @Override
    public List<TicketEntity> getAllTicket() {



        return repoticket.findAll();

    }

    @Override
    public TicketResponse RechercheParId(long id) {
        Optional<TicketEntity> ticketOpt=repoticket.findById(id);
        TicketEntity ticket;
        if(ticketOpt.isPresent())
            ticket=ticketOpt.get();
        else
            throw new NoSuchElementException("Aucun ticket avec cette id");
        return mapper.map(ticket,TicketResponse.class);
    }

    @Override
    public TicketResponse createticket(TicketRequest entityreq) {
    TicketEntity entity=mapper.map(entityreq,TicketEntity.class);
    entity.setDate(Instant.now());
        Optional<ClientEntity>  opt=repoclient.findById(entityreq.getClient().getId());
        if (opt.isPresent()){
            entity.setClient(opt.get());
        }else{
            throw new NoSuchElementException("client id est incorrect ");
        }


        List<MetEntity> mets=entity.getMets();
        entity.setAddition(0);
       for(MetEntity met:mets){
           long metid= met.getId();
           Optional<MetEntity> metopt=repomet.findById(metid);
           if(metopt.isPresent()){
               entity.setAddition(entity.getAddition()+metopt.get().getPrix());
           }else {
               throw new NoSuchElementException("met id est incorrect ");
           }
       }

        TicketEntity tick=repoticket.save(entity);
    return mapper.map(tick,TicketResponse.class);
    }

    @Override
    public TicketResponse modifyTicket(long id, TicketRequest modificationReq) {
        TicketEntity modification=mapper.map(modificationReq,TicketEntity.class);
        TicketResponse oldticket=this.RechercheParId(id);
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
    return mapper.map(oldticket,TicketResponse.class);
    }

    @Override
    public String deleteTicketById(long id) {
        TicketResponse ticket=this.RechercheParId(id);
        repoticket.deleteById(id);
         return "ticket Supprimer";
    }
    @Override
    public ClientEntity ClientplusFidel(Instant debutperiode,Instant finperiode){
       List<TicketEntity>tickets=repoticket.findAll();
       List<TicketEntity>ticketss=new ArrayList<>();

      for(TicketEntity ticket:tickets){
          if(ticket.getDate().isAfter(debutperiode)&&ticket.getDate().isBefore(finperiode)){
ticketss.add(ticket);
          }
      }
     List<ClientEntity>cl=  ticketss.stream().map(tic->tic.getClient()).collect(Collectors.toList());

      ClientEntity fidel=cl.stream().collect(Collectors.groupingBy(l->l, Collectors.counting())).entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
      return fidel;
    }

    @Override
    public float revenudansperiode(Instant debutperiode, Instant finperiode) {
        List<TicketEntity> tickets=repoticket.findAll();
        float somme=0;
        for(TicketEntity ticket:tickets){
            if(ticket.getDate().isAfter(debutperiode)&&ticket.getDate().isBefore(finperiode)){
                somme=ticket.getAddition()+somme;
            }
        }
        return somme;
    }

    @Override
    public Instant JourPlusResrve(long id) {
      Optional  <ClientEntity> client=repoclient.findById(id);
        Instant dateplusrepter=Instant.now();
      if(client.isPresent()){
          dateplusrepter= client.get().getTickets().stream().map(ticket->ticket.getDate())
                 .collect(Collectors.groupingBy(I->I, Collectors.counting()))
                 .entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
      }else throw new NoSuchElementException("client id est incorrect ");
        return dateplusrepter;
    }

}
