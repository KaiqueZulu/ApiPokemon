package com.matdev.ApiPokemon.service;

import com.matdev.ApiPokemon.model.TrainerCard;
import com.matdev.ApiPokemon.repository.TrainerCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerCardService {

    @Autowired
    private TrainerCardRepository repository;

    public TrainerCard add(TrainerCard trainerCard){
        return repository.save(trainerCard);
    }
}
