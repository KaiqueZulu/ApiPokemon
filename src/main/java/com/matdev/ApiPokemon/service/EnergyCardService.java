package com.matdev.ApiPokemon.service;

import com.matdev.ApiPokemon.model.EnergyCard;
import com.matdev.ApiPokemon.repository.EnergyCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnergyCardService {
    @Autowired
    private EnergyCardRepository repository;

    public EnergyCard add(EnergyCard energyCard){
        return repository.save(energyCard);
    }
}
