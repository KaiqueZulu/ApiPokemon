package com.matdev.ApiPokemon.repository;

import com.matdev.ApiPokemon.model.CardPokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardPokemonRepository extends JpaRepository<CardPokemon, Integer> {
}
