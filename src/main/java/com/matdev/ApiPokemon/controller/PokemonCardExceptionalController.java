package com.matdev.ApiPokemon.controller;

import com.matdev.ApiPokemon.model.PokemonCardExceptional;
import com.matdev.ApiPokemon.service.PokemonCardExceptionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cards/pokemon/exceptional")
public class PokemonCardExceptionalController {
    @Autowired
    PokemonCardExceptionalService service;

    @PostMapping("/add")
    public ResponseEntity<PokemonCardExceptional> add(@RequestBody PokemonCardExceptional pokemonCardExceptional){
        PokemonCardExceptional newCard = service.add(pokemonCardExceptional);
        return new ResponseEntity<>(newCard, HttpStatus.CREATED);
    }
}
