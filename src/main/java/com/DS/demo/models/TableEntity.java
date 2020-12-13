package com.DS.demo.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tableresturant")
@EqualsAndHashCode(exclude = "ticktes")
@ToString(exclude = "ticktes")

public class TableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,unique = true)
    private int numero;
    private int nbCouvert;
    private String type;
    private double supplement;
    @OneToMany(mappedBy = "table")

    private List<TicketEntity> ticktes;
}
