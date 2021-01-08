package com.DS.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Met")
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        discriminatorType = DiscriminatorType.STRING,
        name = "Met_type_id",
        columnDefinition = "TEXT"
)
public class MetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,unique = true)
    private String nom;
    private double prix;
    @ManyToMany(mappedBy = "mets")

    @JsonIgnore
    private List<TicketEntity> tickets;

}
