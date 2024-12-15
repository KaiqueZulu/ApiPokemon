package com.matdev.ApiPokemon.service;

import com.matdev.ApiPokemon.exception.CardNotFoundException;
import com.matdev.ApiPokemon.model.TrainerCard;
import com.matdev.ApiPokemon.repository.TrainerCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerCardService {

    @Autowired
    private TrainerCardRepository repository;

    public TrainerCard create(TrainerCard trainerCard) {
        return repository.save(trainerCard);
    }

    public List<TrainerCard> getAll() {
        return repository.findAll();
    }

    public TrainerCard getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new CardNotFoundException("Card with ID " + id + " not found."));
    }

    public TrainerCard update(Integer id, TrainerCard newCard) {
        TrainerCard currentCard = repository.findById(id)
                .orElseThrow(() -> new CardNotFoundException("Card with ID " + id + " not found."));

        updateCard(currentCard, newCard);

        return repository.save(currentCard);
    }

    public String delete(Integer id) {
        TrainerCard card = repository.findById(id)
                .orElseThrow(() -> new CardNotFoundException("Card with ID " + id + " not found."));

        repository.delete(card);

        return "Card deleted successfully.";
    }

    private void updateCard(TrainerCard currentCard, TrainerCard newCard) {
        currentCard.setName(Optional.ofNullable(newCard.getName()).orElse(currentCard.getName()));
        currentCard.setVariant(Optional.ofNullable(newCard.getVariant()).orElse(currentCard.getVariant()));

        if (newCard.getAbout() != null && !newCard.getAbout().isBlank()) {
            currentCard.setAbout(newCard.getAbout());
        }

        if (newCard.getCardEffectArea() != null && !newCard.getCardEffectArea().isEmpty()) {
            currentCard.setCardEffectArea(newCard.getCardEffectArea());
        }
    }
}
