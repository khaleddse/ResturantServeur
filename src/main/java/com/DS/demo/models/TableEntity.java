package com.DS.demo.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "table")
public class TableEntity {
    @Id
    private Integer numero;
    private Integer nbCouvert;
    private String type;
    private double supplement;
    @OneToMany(mappedBy = "table")
    private List<TicketEntity> ticktes;
}
