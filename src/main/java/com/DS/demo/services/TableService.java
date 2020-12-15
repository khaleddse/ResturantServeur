package com.DS.demo.services;

import com.DS.demo.DTO.TableRequest;
import com.DS.demo.DTO.TableResponse;
import com.DS.demo.models.TableEntity;


import java.util.List;

public interface TableService {
    List<TableEntity> getAllTables();

    TableResponse RechercheParId(long id);

    TableResponse ajoutetable(TableRequest entity);

    TableResponse modifyTable(long id, TableRequest modification);

   String deleteTableById(long id);

    TableResponse RechercheTableParNum(int num);
}
