package com.DS.demo.services;

import com.DS.demo.DTO.TableRequest;
import com.DS.demo.DTO.TableResponse;
import com.DS.demo.models.ClientEntity;
import com.DS.demo.models.TableEntity;
import com.DS.demo.repositories.TableRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class TableServiceImpl implements TableService{

    TableRepo repotable;
    private ModelMapper mapper = new ModelMapper();
    @Autowired
    public TableServiceImpl(TableRepo repotable) {
        this.repotable = repotable;
    }

    @Override
    public List<TableEntity> getAllTables() {
        return repotable.findAll() ;
    }

    @Override
    public TableResponse RechercheParId(long id) {
        Optional<TableEntity> tableOpt = repotable.findById(id);
        TableEntity table;
        if (tableOpt.isPresent())
            table = tableOpt.get();
        else
            throw new NoSuchElementException("aucun table existe avec cette Id");
        return mapper.map(table,TableResponse.class);
    }

    @Override
    public TableResponse ajoutetable(TableRequest entityreq) {
        TableEntity entity=   mapper.map(entityreq,TableEntity.class);
        TableEntity table=repotable.save(entity);
        return mapper.map(table,TableResponse.class);
    }

    @Override
    public TableResponse modifyTable(long id, TableRequest modificationreq) {
        TableEntity modification = mapper.map(modificationreq,TableEntity.class);
        TableResponse oldtableres= this.RechercheParId(id);
        TableEntity oldtable=mapper.map(oldtableres,TableEntity.class);
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
        TableEntity table=repotable.save(oldtable);
        return mapper.map(table,TableResponse.class);

    }

    @Override
    public String deleteTableById(long id) {
        TableResponse table=this.RechercheParId(id);
      repotable.deleteById(id);
      return "table supprimer";
    }

    @Override
    public TableResponse RechercheTableParNum(int num) {

        Optional<TableEntity> opt= repotable.findByNumero(num);
        TableEntity table;
        if (opt.isPresent())
            table= opt.get();
        else
            throw new NoSuchElementException("\"table avec cette numerp n'existe pas\"");
        return mapper.map(table,TableResponse.class);
    }
}
