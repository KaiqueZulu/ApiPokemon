package com.matdev.ApiPokemon.controller;

import com.matdev.ApiPokemon.model.TrainerCard;
import com.matdev.ApiPokemon.service.TrainerCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards/trainer")
public class TrainerCardController {

    @Autowired
    private TrainerCardService service;

    @PostMapping
    public ResponseEntity<TrainerCard> create(@RequestBody TrainerCard trainerCard) {
        TrainerCard newCard = service.create(trainerCard);
        return new ResponseEntity<>(newCard, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TrainerCard>> getAll() {
        List<TrainerCard> response = service.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerCard> getById(@PathVariable Integer id) {
        TrainerCard response = service.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainerCard> update(@PathVariable Integer id, @RequestBody TrainerCard newCard) {
        TrainerCard response = service.update(id, newCard);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        String response = service.delete(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
