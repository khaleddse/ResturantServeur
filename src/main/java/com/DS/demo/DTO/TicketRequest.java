package com.DS.demo.DTO;

import com.DS.demo.models.ClientEntity;
import com.DS.demo.models.MetEntity;
import com.DS.demo.models.TableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {
    private Integer numero;
    private Instant date;
    private Integer nbCouvert;
    private float addition;
    private ClientRequest client;
    private TableRequest table;

    private List<MetRequest> mets;
}
