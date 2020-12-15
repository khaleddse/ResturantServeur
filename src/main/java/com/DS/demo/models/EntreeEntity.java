package com.DS.demo.models;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@DiscriminatorValue("Entrée")
public class EntreeEntity extends MetEntity {



    }
