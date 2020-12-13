package com.DS.demo.services;

import com.DS.demo.models.TableEntity;


import java.util.List;

public interface TableService {
    List<TableEntity> getAllTables();

    TableEntity RechercheParId(long id);

    TableEntity ajoutetable(TableEntity entity);

    TableEntity modifyTable(long id, TableEntity modification);

   String deleteTableById(long id);

   TableEntity RechercheTableParNum(int num);
}
