package com.matdev.ApiPokemon.controller;

import com.matdev.ApiPokemon.model.EnergyCard;
import com.matdev.ApiPokemon.model.ErrorResponse;
import com.matdev.ApiPokemon.service.EnergyCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<EnergyCard> response = service.getById(id);
        if(response.isPresent()){
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }else{
            ErrorResponse errorResponse = new ErrorResponse("The mentioned letter ID does not match any records found.");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody EnergyCard newCard){
        Optional<EnergyCard> response = service.update(id, newCard);

        if(response.isPresent()){
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }else{
            ErrorResponse errorResponse = new ErrorResponse("The mentioned letter ID does not match any records found.");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
}
