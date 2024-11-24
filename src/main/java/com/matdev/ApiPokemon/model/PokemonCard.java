package com.matdev.ApiPokemon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "PokemonCards")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PokemonCard extends PokemonCardBase {
    private float height;
    private float weight;

}
