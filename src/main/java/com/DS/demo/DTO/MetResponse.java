package com.DS.demo.DTO;

import com.DS.demo.models.TicketEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetResponse {
    private long id;
    private String nom;
    private double prix;
    private List<TicketEntity> tickets;
}
