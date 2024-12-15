package com.matdev.ApiPokemon.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PokemonCard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private Integer life;

    @ManyToOne
    @JoinColumn(name = "pokemon_id")
    private Pokemon pokemon;

    private HashMap<String, String> ability;
    private HashMap<String, String> cardBody;
    private HashMap<String, String> collectorInformation;
    private String exceptionalType = "";
    private HashMap<String, String> exceptionalRule = new HashMap<>();
    private String about;
}
