package com.DS.demo.models;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "Entree")
public class EntreeEntity extends MetEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

    }
