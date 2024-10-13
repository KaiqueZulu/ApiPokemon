package com.matdev.ApiPokemon.controller;

import com.matdev.ApiPokemon.model.CardPokemon;
import com.matdev.ApiPokemon.service.CardPokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cards")
public class CardPokemonController {

    @Autowired
    private CardPokemonService service;

    @PostMapping("/pokemon/add")
    public ResponseEntity<CardPokemon> addCardPokemon(@RequestBody CardPokemon cardPokemon){
        CardPokemon newCardPokemon = service.createCardPokemon(cardPokemon);
        return new ResponseEntity<>(newCardPokemon, HttpStatus.CREATED);
    }

    @PostMapping("/trainer/add")
    public ResponseEntity<CardPokemon> addCardTrainer(@RequestBody CardPokemon cardTrainer){
        CardPokemon newCardTrainer = service.createCardPokemon(cardTrainer);
        return new ResponseEntity<>(newCardTrainer, HttpStatus.CREATED);
    }

    @PostMapping("/power/add")
    public ResponseEntity<CardPokemon> addCardPower(@RequestBody CardPokemon cardPower){
        CardPokemon newCardPower = service.createCardPokemon(cardPower);
        return new ResponseEntity<>(newCardPower, HttpStatus.CREATED);
    }

}
