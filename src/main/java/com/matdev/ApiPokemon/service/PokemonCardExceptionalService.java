package com.matdev.ApiPokemon.service;

import com.matdev.ApiPokemon.model.PokemonCardExceptional;
import com.matdev.ApiPokemon.repository.PokemonCardExceptionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonCardExceptionalService {

    @Autowired
    PokemonCardExceptionalRepository repository;

    public PokemonCardExceptional add(PokemonCardExceptional pokemonCardExceptional){
        return repository.save(pokemonCardExceptional);
    }
}
