package com.DS.demo.services;


import com.DS.demo.DTO.*;
import com.DS.demo.models.*;
import com.DS.demo.repositories.ClientRepo;
import com.DS.demo.repositories.MetRepo;
import com.DS.demo.repositories.TableRepo;
import com.DS.demo.repositories.TicketRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TicketSeviceImpl implements TicketService{

    TicketRepo repoticket;
    ClientRepo repoclient;
    MetRepo repomet;
    TableRepo repotable;

    private ModelMapper mapper = new ModelMapper();
    @Autowired
    public TicketSeviceImpl(TicketRepo repoticket, ClientRepo repoclient, MetRepo repomet, TableRepo repotable) {
        this.repoticket = repoticket;
        this.repoclient = repoclient;
        this.repomet = repomet;
        this.repotable = repotable;
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
    public MetResponse PlatPlusacheter(LocalDate begin, LocalDate  end){
        List<TicketEntity> tickets=repoticket.findAll();
        List<Long> idList=new ArrayList<>();
        for (TicketEntity ticket:tickets){

            if(ticket.getDate().isAfter(begin.atStartOfDay(ZoneId.systemDefault()).toInstant())&&ticket.getDate().isBefore(end.atStartOfDay(ZoneId.systemDefault()).toInstant())){

                for (MetEntity met:ticket.getMets()){

                    if(met instanceof PlatEntity){
                        idList.add(met.getId());
                    }
                }
            }
        }
        Long metid= idList.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
        MetEntity met=repomet.findById(metid).get();
        return mapper.map(met,MetResponse.class);
    }



    @Override
    public ClientRespence ClientplusFidel(){
        List<TicketEntity>tickets=repoticket.findAll();

        List<ClientEntity>cl=  tickets.stream().map(tic->tic.getClient()).collect(Collectors.toList());

        ClientEntity fidel=cl.stream().collect(Collectors.groupingBy(l->l, Collectors.counting())).entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
        return mapper.map(fidel,ClientRespence.class);
    }

    @Override
    public TableResponse TablePlusReserver(){
        List<TicketEntity>tickets=repoticket.findAll();
        List<TableEntity>cl=  tickets.stream().map(tic->tic.getTable()).collect(Collectors.toList());
        TableEntity fidel=cl.stream().collect(Collectors.groupingBy(l->l, Collectors.counting())).entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
        return mapper.map(fidel,TableResponse.class);

    }



    @Override
    public LocalDate JourPlusResrve(long id) {
      Optional  <ClientEntity> client=repoclient.findById(id);
        LocalDate dateplusrepter=LocalDate.now();
      if(client.isPresent()){
          dateplusrepter= client.get().getTickets().stream().map(ticket->ticket.getDate().atZone(ZoneId.systemDefault()).toLocalDate())
                 .collect(Collectors.groupingBy(I->I, Collectors.counting()))
                 .entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
      }else throw new NoSuchElementException("client id est incorrect ");
        return dateplusrepter;
    }



    @Override
    public String RevenueDerniere(){
        List<TicketEntity> tickets=repoticket.findAll();
        double revenueJours=0,revenueSemaine=0,revenuemois=0;
        for (TicketEntity ticket:tickets){
            if (ticket.getDate().isAfter(Instant.now().minus(Period.ofDays(30)))){
                revenuemois=revenuemois+ticket.getAddition();
            }
            if (ticket.getDate().isAfter(Instant.now().minus(Period.ofDays(7)))){
                revenueSemaine=revenueSemaine+ticket.getAddition();
            }
            if (ticket.getDate().isAfter(Instant.now().minus(Period.ofDays(1)))){
                revenueJours=revenueJours+ticket.getAddition();
            }
        }

        return "Revenue moins derniere :"+revenuemois+"\n Revenue semaine derniere :"+revenueSemaine+"\n Revenue jour derniere :"+revenueJours;
    }

    @Override
    public float revenudansperiode(LocalDate debutperiode, LocalDate finperiode) {
        List<TicketEntity> tickets=repoticket.findAll();
        float somme=0;
        for(TicketEntity ticket:tickets){
            if(ticket.getDate().isAfter(debutperiode.atStartOfDay(ZoneId.systemDefault()).toInstant())&&ticket.getDate().isBefore(finperiode.atStartOfDay(ZoneId.systemDefault()).toInstant())){
                somme=ticket.getAddition()+somme;
            }
        }
        return somme;
    }



}
