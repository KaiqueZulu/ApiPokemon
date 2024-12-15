package com.matdev.ApiPokemon.service;

import com.matdev.ApiPokemon.exception.PokemonNotFoundException;
import com.matdev.ApiPokemon.model.Pokemon;
import com.matdev.ApiPokemon.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {
    @Autowired
    private PokemonRepository repository;

    public Pokemon create(Pokemon pokemon) {
        return repository.save(pokemon);
    }

    public List<Pokemon> getAll() {
        return repository.findAll();
    }

    public Pokemon getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon with ID " + id + " not found."));
    }

    public long count() {
        return repository.count();
    }
}
