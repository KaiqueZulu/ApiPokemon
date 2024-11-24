package com.matdev.ApiPokemon.model;

import com.matdev.ApiPokemon.enums.PokemonType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PokemonCardBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private Integer life;
    private PokemonType type;
    private String stage;
    private String evolvesFrom;
    private String evolvesTo;
    private HashMap<String, String> ability;
    private HashMap<String, String> cardBody;
    private HashMap<PokemonType, String> weakness;
    private HashMap<PokemonType, String> resistance;
    private String retreatValue;
    private HashMap<String, String> collectorInformation;
    private String about;
}
