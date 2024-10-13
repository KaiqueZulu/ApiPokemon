package com.matdev.ApiPokemon.controller;

import com.matdev.ApiPokemon.model.EnergyCard;
import com.matdev.ApiPokemon.service.EnergyCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cards/energy")
public class EnergyCardController {

    @Autowired
    private EnergyCardService service;

    @PostMapping("/add")
    public ResponseEntity<EnergyCard> add(@RequestBody EnergyCard energyCard){
        EnergyCard newCard = service.add(energyCard);
        return new ResponseEntity<>(newCard, HttpStatus.CREATED);
    }
}
