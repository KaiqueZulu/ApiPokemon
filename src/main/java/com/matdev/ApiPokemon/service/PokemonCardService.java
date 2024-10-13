package com.matdev.ApiPokemon.service;

import com.matdev.ApiPokemon.model.PokemonCard;
import com.matdev.ApiPokemon.repository.PokemonCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonCardService {

    @Autowired
    private PokemonCardRepository repository;

    public PokemonCard add(PokemonCard pokemonCard){
        return repository.save(pokemonCard);
    }
}
