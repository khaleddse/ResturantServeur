package com.DS.demo.endpoints;

import com.DS.demo.DTO.TableRequest;
import com.DS.demo.DTO.TableResponse;
import com.DS.demo.models.TableEntity;
import com.DS.demo.services.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/table")
public class TableRest {
private TableService service;

@Autowired
    public TableRest(TableService service) {
        super();
        this.service = service;
    }
    @GetMapping
    public List<TableEntity> getAllTables(){
        return service.getAllTables();
    }
    @GetMapping("/{id}")
    TableResponse RechercheParId(@PathVariable("id") long id) {
        return service.RechercheParId(id);
    }
    @PostMapping("/add")
    TableResponse ajoutetable(@RequestBody TableRequest entity){
    return service.ajoutetable(entity);
    }
@PostMapping("/update/{id}")
TableResponse modifyTable(@PathVariable("id") long id, @RequestBody TableRequest modification){
    return service.modifyTable(id,modification);
}
@DeleteMapping("/delete/{id}")
    String deleteTableById(@PathVariable("id") long id){
    return service.deleteTableById(id);
}
@GetMapping("/recherche/{num}")
TableResponse RechercheTableParNum(@PathVariable("num") int num){
    return service.RechercheTableParNum(num);
}
}
