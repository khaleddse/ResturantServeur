package com.DS.demo.models;

import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Met")
@Data
public class MetEntity {
    @Id
    private String nom;
    private double prix;
    @ManyToMany(mappedBy = "Met")
    private List<TicketEntity> tickets;

}
