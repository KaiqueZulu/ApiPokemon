package com.matdev.ApiPokemon.service;

import com.matdev.ApiPokemon.exception.CardNotFoundException;
import com.matdev.ApiPokemon.model.EnergyCard;
import com.matdev.ApiPokemon.repository.EnergyCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnergyCardService {
    @Autowired
    private EnergyCardRepository repository;

    public EnergyCard create(EnergyCard energyCard){
        return repository.save(energyCard);
    }

    public List<EnergyCard> getAll(){
        return repository.findAll();
    }

    public EnergyCard getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new CardNotFoundException("Card with ID " + id + " not found."));
    }

    public EnergyCard update(Integer id, EnergyCard newCard) {
        EnergyCard currentCard = repository.findById(id)
                .orElseThrow(() -> new CardNotFoundException("Card with ID " + id + " not found."));
        updateCard(currentCard, newCard);
        return repository.save(currentCard);
    }

    public String delete(Integer id) {
        EnergyCard card = repository.findById(id)
                .orElseThrow(() -> new CardNotFoundException("Card with ID " + id + " not found."));

        repository.delete(card);

        return "Card deleted successfully.";
    }

    private void updateCard(EnergyCard currentCard, EnergyCard newCard) {
        currentCard.setName(Optional.ofNullable(newCard.getName()).orElse(currentCard.getName()));
        currentCard.setType(Optional.ofNullable(newCard.getType()).orElse(currentCard.getType()));

        if (newCard.getAbout() != null && !newCard.getAbout().isBlank()) {
            currentCard.setAbout(newCard.getAbout());
        }
    }
}