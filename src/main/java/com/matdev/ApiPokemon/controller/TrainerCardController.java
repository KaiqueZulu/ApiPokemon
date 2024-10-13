package com.matdev.ApiPokemon.controller;

import com.matdev.ApiPokemon.model.TrainerCard;
import com.matdev.ApiPokemon.service.TrainerCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cards/trainer")
public class TrainerCardController {

    @Autowired
    private TrainerCardService service;

    @PostMapping("/add")
    public ResponseEntity<TrainerCard> add(@RequestBody TrainerCard trainerCard){
        TrainerCard newCard = service.add(trainerCard);
        return new ResponseEntity<>(newCard, HttpStatus.CREATED);
    }
}
