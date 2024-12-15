package com.matdev.ApiPokemon.repository;

import com.matdev.ApiPokemon.model.PokemonCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonCardRepository extends JpaRepository<PokemonCard, Integer> {
}
