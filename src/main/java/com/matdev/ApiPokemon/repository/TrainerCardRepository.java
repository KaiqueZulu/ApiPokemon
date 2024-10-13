package com.matdev.ApiPokemon.repository;

import com.matdev.ApiPokemon.model.TrainerCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerCardRepository extends JpaRepository<TrainerCard, Integer> {
}
