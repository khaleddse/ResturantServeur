package com.DS.demo.services;

import com.DS.demo.models.TableEntity;
import com.DS.demo.models.TicketEntity;

import java.util.List;

public interface TableService {
    List<TableEntity> getAllTables();

    TableEntity RechercheParId(Integer id);

    TableEntity ajoutetable(TableEntity entity);

    TableEntity modifyTable(Integer id, TableEntity modification);

    TableEntity deleteTableById(Integer id);
}
