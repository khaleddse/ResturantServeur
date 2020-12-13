package com.DS.demo.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "table")
public class TableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,unique = true)
    private Integer numero;
    private Integer nbCouvert;
    private String type;
    private double supplement;
    @OneToMany(mappedBy = "table")
    private List<TicketEntity> ticktes;
}
