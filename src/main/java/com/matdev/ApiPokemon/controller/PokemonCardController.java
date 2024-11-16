package com.matdev.ApiPokemon.controller;

import com.matdev.ApiPokemon.model.PokemonCard;
import com.matdev.ApiPokemon.service.PokemonCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards/pokemon")
public class PokemonCardController {

    @Autowired
    private PokemonCardService service;

    @PostMapping()
    public ResponseEntity<PokemonCard> add(@RequestBody PokemonCard pokemonCard){
        PokemonCard response = service.create(pokemonCard);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PokemonCard>> getAll(){
        List<PokemonCard> response = service.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonCard> getById(@PathVariable Integer id){
        PokemonCard response = service.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PokemonCard> update(@PathVariable Integer id, @RequestBody PokemonCard newCard){
        PokemonCard response = service.update(id, newCard);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.ok("Card deleted successfully.");
    }
}
