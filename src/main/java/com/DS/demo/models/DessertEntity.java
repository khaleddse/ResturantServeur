package com.DS.demo.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Dessert")
public class DessertEntity extends MetEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

}
