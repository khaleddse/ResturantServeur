package com.DS.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {
    private Integer numero;
    private Instant date;
    private Integer nbCouvert;
    private float addition;

}
