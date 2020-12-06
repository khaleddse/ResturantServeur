package com.DS.demo.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Ticket")
@Data
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numero;
    private LocalDate date;
    private Integer nbCouvert;
    private float addition;
    @ManyToOne
    private ClientEntity client;
    @ManyToOne
    private TableEntity table;
    @ManyToMany
    @JoinTable(name = "TicketMet", joinColumns=@JoinColumn(name = "tickets"), inverseJoinColumns = @JoinColumn(name = "mets"))
    private List<MetEntity> mets;

}
