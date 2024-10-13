package com.matdev.ApiPokemon.model;

import com.matdev.ApiPokemon.enums.PokemonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EnergyCards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnergyCard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name = "Energy";
    private PokemonType type;
    private String about = "";
}
