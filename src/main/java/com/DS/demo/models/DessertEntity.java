package com.DS.demo.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@DiscriminatorValue("Dessert")
public class DessertEntity extends MetEntity{


}
