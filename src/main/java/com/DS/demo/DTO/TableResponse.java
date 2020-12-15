package com.DS.demo.DTO;

import com.DS.demo.models.TicketEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableResponse {
    private long id;

    private int numero;
    private int nbCouvert;
    private String type;
    private double supplement;
    private List<TicketEntity> ticktes;
}
