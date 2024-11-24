package com.matdev.ApiPokemon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ExceptionalPokemonCards")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PokemonCardExceptional extends PokemonCardBase {
    private String exceptionalType;
    private HashMap<String, String> exceptionalRule;
}
