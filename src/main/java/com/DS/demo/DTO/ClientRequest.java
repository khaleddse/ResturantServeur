package com.DS.demo.DTO;

import com.DS.demo.models.TicketEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {
    private long id;
    private String nom;
    private String prenom;
    private LocalDate dateDeNaissance;
    private String courriel;
    private String telephone;
    private List<TicketEntity> tickets;
}
