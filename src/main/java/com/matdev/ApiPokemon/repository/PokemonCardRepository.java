package com.matdev.ApiPokemon.repository;

import com.matdev.ApiPokemon.model.PokemonCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonCardRepository extends JpaRepository<PokemonCard, Integer> {
}
