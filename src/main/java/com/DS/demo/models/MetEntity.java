package com.DS.demo.models;

import lombok.Data;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Met")
@Data
public class MetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,unique = true)
    private String nom;
    private double prix;
    private String type;
    @ManyToMany(mappedBy = "mets")
    private List<TicketEntity> tickets;

}
