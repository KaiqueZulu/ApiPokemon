package com.matdev.ApiPokemon.controller;

import com.matdev.ApiPokemon.model.PokemonCard;
import com.matdev.ApiPokemon.service.PokemonCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cards/pokemon")
public class PokemonCardController {

    @Autowired
    private PokemonCardService service;

    @PostMapping("/add")
    public ResponseEntity<PokemonCard> add(@RequestBody PokemonCard pokemonCard){
        PokemonCard newCard = service.add(pokemonCard);
        return new ResponseEntity<>(newCard, HttpStatus.CREATED);
    }
}
