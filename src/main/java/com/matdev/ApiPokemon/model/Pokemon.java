package com.matdev.ApiPokemon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pokemon")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pokemon {
    @Id
    private int id;
    private String name;
    private String type;
    private String heightFt;
    private float heightM;
    private float weightLbs;
    private float weightKgs;
}
