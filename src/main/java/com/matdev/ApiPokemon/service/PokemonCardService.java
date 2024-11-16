package com.matdev.ApiPokemon.service;

import com.matdev.ApiPokemon.exception.CardNotFoundException;
import com.matdev.ApiPokemon.model.PokemonCard;
import com.matdev.ApiPokemon.repository.PokemonCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonCardService {

    @Autowired
    private PokemonCardRepository repository;

    public PokemonCard create(PokemonCard pokemonCard) {
        return repository.save(pokemonCard);
    }

    public List<PokemonCard> getAll(){
        return repository.findAll();
    }

    public PokemonCard getById(Integer id){
        return repository.findById(id)
                .orElseThrow(() -> new CardNotFoundException("Card with ID " + id + " not found."));
    }

    public PokemonCard update(Integer id, PokemonCard newCard){
        PokemonCard currentCard = repository.findById(id)
                .orElseThrow(() -> new CardNotFoundException("Card with ID " + id + " not found."));
        updateCard(currentCard, newCard);

        return repository.save(currentCard);
    }

    public void delete(Integer id){
        PokemonCard card = repository.findById(id)
                .orElseThrow(() -> new CardNotFoundException("Card with ID " + id + " not found."));
        repository.delete(card);
    }

    private void updateCard(PokemonCard currentCard, PokemonCard newCard) {

        currentCard.setName(Optional.ofNullable(newCard.getName()).orElse(currentCard.getName()));

        currentCard.setLife(Optional.ofNullable(newCard.getLife()).orElse(currentCard.getLife()));

        currentCard.setType(Optional.ofNullable(newCard.getType()).orElse(currentCard.getType()));

        currentCard.setStage(Optional.ofNullable(newCard.getStage()).orElse(currentCard.getStage()));

        currentCard.setEvolvesFrom(Optional.ofNullable(newCard.getEvolvesFrom()).orElse(currentCard.getEvolvesFrom()));

        currentCard.setEvolvesTo(Optional.ofNullable(newCard.getEvolvesTo()).orElse(currentCard.getEvolvesTo()));

        currentCard.setHeight(newCard.getHeight() != 0.0F ? newCard.getHeight() : currentCard.getHeight());

        currentCard.setWeight(newCard.getWeight() != 0.0F ? newCard.getWeight() : currentCard.getWeight());

        if (newCard.getAbility() != null && !newCard.getAbility().isEmpty()) {
            currentCard.getAbility().putAll(newCard.getAbility());
        }

        if (newCard.getCardBody() != null && !newCard.getCardBody().isEmpty()) {
            currentCard.getCardBody().putAll(newCard.getCardBody());
        }

        if (newCard.getWeakness() != null && !newCard.getWeakness().isEmpty()) {
            currentCard.getWeakness().putAll(newCard.getWeakness());
        }

        if (newCard.getResistance() != null && !newCard.getResistance().isEmpty()) {
            currentCard.getResistance().putAll(newCard.getResistance());
        }

        currentCard.setRetreatValue(Optional.ofNullable(newCard.getRetreatValue()).orElse(currentCard.getRetreatValue()));

        if (newCard.getCollectorInformation() != null && !newCard.getCollectorInformation().isEmpty()) {
            currentCard.getCollectorInformation().putAll(newCard.getCollectorInformation());
        }

        if (newCard.getAbout() != null && !newCard.getAbout().isBlank()) {
            currentCard.setAbout(newCard.getAbout());
        }
    }

}
