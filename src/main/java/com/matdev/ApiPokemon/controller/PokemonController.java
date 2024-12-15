package com.matdev.ApiPokemon.controller;

import com.matdev.ApiPokemon.model.Pokemon;
import com.matdev.ApiPokemon.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {
    @Autowired
    private PokemonService service;

    @GetMapping
    public ResponseEntity<List<Pokemon>> getAll() {
        List<Pokemon> response = service.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getById(@PathVariable Integer id) {
        Pokemon response = service.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
