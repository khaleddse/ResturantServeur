package com.DS.demo.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Client")
@Data

public class ClientEntity {

private String nom;
private String prenom;
private LocalDate dateDeNaissance;
private String courriel;
@Id
private String telephone;
@OneToMany(mappedBy = "Client")
private List<TicketEntity> tickets;

}
