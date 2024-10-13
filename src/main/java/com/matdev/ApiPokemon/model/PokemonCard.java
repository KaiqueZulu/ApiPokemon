package com.matdev.ApiPokemon.model;

import com.matdev.ApiPokemon.enums.PokemonType;
import com.matdev.ApiPokemon.enums.TrainerVariant;
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
    private String phase;
    private String evolvesFrom;
    private String evolvesTo;
    private float height;
    private float weight;
    private HashMap<PokemonType, String> weakness;
    private HashMap<PokemonType, String> resistance;
    private String retreatValue;
    private String about;
    private HashMap<String, String> cardAttackArea;
}
