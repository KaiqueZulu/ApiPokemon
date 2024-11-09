package com.matdev.ApiPokemon.controller;

import com.matdev.ApiPokemon.model.EnergyCard;
import com.matdev.ApiPokemon.service.EnergyCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cards/energy")
public class EnergyCardController {

    @Autowired
    private EnergyCardService service;

    @PostMapping
    public ResponseEntity<EnergyCard> create(@RequestBody EnergyCard newCard){
        EnergyCard response = service.create(newCard);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EnergyCard>> getAll(){
        List<EnergyCard> response = service.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        EnergyCard response = service.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody EnergyCard newCard){
        EnergyCard response = service.update(id, newCard);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok("Card deleted successfully.");
    }
}
