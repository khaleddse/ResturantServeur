package com.DS.demo.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Ticket")
@Data
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true,nullable = false)
    private Integer numero;
    private LocalDateTime date;
    private Integer nbCouvert;
    private float addition;
    @ManyToOne
    private ClientEntity client;
    @ManyToOne
    private TableEntity table;

    @ManyToMany
    @JoinTable(name = "compse")
    private List<MetEntity> mets;

}
