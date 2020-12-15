package com.DS.demo.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@DiscriminatorValue("Plat")
public class PlatEntity extends MetEntity {

}
