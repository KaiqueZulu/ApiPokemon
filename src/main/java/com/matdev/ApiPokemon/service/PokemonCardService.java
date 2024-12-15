package com.matdev.ApiPokemon.service;

import com.matdev.ApiPokemon.exception.CardNotFoundException;
import com.matdev.ApiPokemon.exception.PokemonNotFoundException;
import com.matdev.ApiPokemon.model.Pokemon;
import com.matdev.ApiPokemon.model.PokemonCard;
import com.matdev.ApiPokemon.repository.PokemonCardRepository;
import com.matdev.ApiPokemon.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonCardService {

    @Autowired
    private PokemonCardRepository cardRepository;

    @Autowired
    private PokemonRepository pokemonRepository;

    public PokemonCard create(PokemonCard pokemonCard) {

        Pokemon pokemon = pokemonRepository.findById(pokemonCard.getPokemon().getId())
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon with ID " + pokemonCard.getPokemon().getId() + " not found"));

        pokemonCard.setPokemon(pokemon);

        return cardRepository.save(pokemonCard);
    }

    public List<PokemonCard> getAll(){
        return cardRepository.findAll();
    }

    public PokemonCard getById(Integer id){
        return cardRepository.findById(id)
                .orElseThrow(() -> new CardNotFoundException("Card with ID " + id + " not found."));
    }

    public PokemonCard update(Integer id, PokemonCard newCard){
        PokemonCard currentCard = cardRepository.findById(id)
                .orElseThrow(() -> new CardNotFoundException("Card with ID " + id + " not found."));
        updateCard(currentCard, newCard);

        return cardRepository.save(currentCard);
    }

    public String delete(Integer id) {
        PokemonCard card = cardRepository.findById(id)
                .orElseThrow(() -> new CardNotFoundException("Card with ID " + id + " not found."));
        cardRepository.delete(card);

        return "Card deleted successfully.";
    }

    private void updateCard(PokemonCard currentCard, PokemonCard newCard) {
        currentCard.setLife(Optional.ofNullable(newCard.getLife()).orElse(currentCard.getLife()));

        if (newCard.getPokemon() != null && !newCard.getPokemon().equals(currentCard.getPokemon())) {
            currentCard.setPokemon(newCard.getPokemon());
        }

        if (newCard.getAbility() != null && !newCard.getAbility().isEmpty()) {
            currentCard.getAbility().putAll(newCard.getAbility());
        }

        if (newCard.getCardBody() != null && !newCard.getCardBody().isEmpty()) {
            currentCard.getCardBody().putAll(newCard.getCardBody());
        }

        if (newCard.getCollectorInformation() != null && !newCard.getCollectorInformation().isEmpty()) {
            currentCard.getCollectorInformation().putAll(newCard.getCollectorInformation());
        }

        if (newCard.getAbout() != null && !newCard.getAbout().isBlank()) {
            currentCard.setAbout(newCard.getAbout());
        }

        if (newCard.getExceptionalType() != null && !newCard.getExceptionalType().isBlank()) {
            currentCard.setExceptionalType(newCard.getExceptionalType());
        }

        if (newCard.getExceptionalRule() != null && !newCard.getExceptionalRule().isEmpty()) {
            currentCard.setExceptionalRule(newCard.getExceptionalRule());
        }
    }

}
