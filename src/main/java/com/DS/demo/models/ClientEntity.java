package com.DS.demo.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Client")
@Data

public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
private String nom;
private String prenom;
private LocalDate dateDeNaissance;
private String courriel;
private String telephone;
@OneToMany(mappedBy = "client")
private List<TicketEntity> tickets;

}
