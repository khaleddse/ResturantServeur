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
    public TableEntity RechercheParId(long id) {
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
        return repotable.save(entity);
    }

    @Override
    public TableEntity modifyTable(long id, TableEntity modification) {
        TableEntity oldtable= this.RechercheParId(id);
        if(modification.getNumero()!=0){
            oldtable.setNumero(modification.getNumero());
        }
        if(modification.getNbCouvert()!=0){
            oldtable.setNbCouvert(modification.getNbCouvert());
        }
        if(modification.getSupplement() !=0){
            oldtable.setSupplement(modification.getSupplement());
        }
        if(modification.getType() !=null){
            oldtable.setType(modification.getType());
        }
        return repotable.save(oldtable);

    }

    @Override
    public String deleteTableById(long id) {
      TableEntity table=this.RechercheParId(id);
      repotable.deleteById(id);
      return "table supprimer";
    }

    @Override
    public TableEntity RechercheTableParNum(int num) {
        return repotable.findByNumero(num)
                .orElseThrow(()-> new NoSuchElementException("table avec cette numerp n'existe pas"));
    }
}
