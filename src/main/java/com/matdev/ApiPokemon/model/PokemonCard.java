package com.matdev.ApiPokemon.model;

import com.matdev.ApiPokemon.enums.PokemonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashMap;

@Entity
@Table(name = "PokemonCards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonCard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private Integer life;
    private PokemonType type;
    private String stage;
    private String evolvesFrom;
    private String evolvesTo;
    private float height;
    private float weight;
    private HashMap<String, String> ability;
    private HashMap<String, String> cardBody;
    private HashMap<PokemonType, String> weakness;
    private HashMap<PokemonType, String> resistance;
    private String retreatValue;
    private HashMap<String, String> collectorInformation;
    private String about;
}
