package com.matdev.ApiPokemon.service;

import com.matdev.ApiPokemon.model.CardPokemon;
import com.matdev.ApiPokemon.repository.CardPokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardPokemonService {

    @Autowired
    private CardPokemonRepository repository;

    public CardPokemon createCardPokemon(CardPokemon cardPokemon){
        return repository.save(cardPokemon);
    }

    public CardPokemon createCardTrainer(CardPokemon cardPokemon){
        return repository.save(cardPokemon);
    }

    public CardPokemon createCardPower(CardPokemon cardPokemon){
        return repository.save(cardPokemon);
    }

}
