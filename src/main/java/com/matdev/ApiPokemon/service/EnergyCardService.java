package com.matdev.ApiPokemon.service;

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

    public Optional<EnergyCard> getById(Integer id){
        return repository.findById(id);
    }

    public Optional<EnergyCard> update(Integer id, EnergyCard newCard) {
        Optional<EnergyCard> existingCardOpt = repository.findById(id);

        if (existingCardOpt.isPresent()) {
            EnergyCard currentCard = existingCardOpt.get();

            currentCard.setName(newCard.getName());
            currentCard.setType(newCard.getType());
            currentCard.setAbout(newCard.getAbout());

            repository.save(currentCard);
            return Optional.of(currentCard);
        }
        return Optional.empty();
    }
}
