package com.DS.demo.services;

import com.DS.demo.models.TableEntity;
import com.DS.demo.repositories.TableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class TableServiceImpl implements TableService{

    TableRepo repotable;
    @Autowired
    public TableServiceImpl(TableRepo repotable) {
        this.repotable = repotable;
    }

    @Override
    public List<TableEntity> getAllTables() {
        return repotable.findAll() ;
    }

    @Override
    public TableEntity RechercheParId(Integer id) {
        Optional<TableEntity> tableOpt = repotable.findById(id);
        TableEntity table;
        if (tableOpt.isPresent())
            table = tableOpt.get();
        else
            throw new NoSuchElementException("aucun table existe avec cette Id");
        return table;
    }

    @Override
    public TableEntity ajoutetable(TableEntity entity) {
        return null;
    }

    @Override
    public TableEntity modifyTable(Integer id, TableEntity modification) {
        return null;
    }

    @Override
    public TableEntity deleteTableById(Integer id) {
      TableEntity table=this.getAllTables();
      repotable.deleteById(id);
      return table;
    }
}
