package com.matdev.ApiPokemon.repository;

import com.matdev.ApiPokemon.model.PokemonCardExceptional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonCardExceptionalRepository extends JpaRepository<PokemonCardExceptional, Integer> {
}
