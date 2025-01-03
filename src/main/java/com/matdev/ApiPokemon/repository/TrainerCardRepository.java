package com.matdev.ApiPokemon.repository;

import com.matdev.ApiPokemon.model.TrainerCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerCardRepository extends JpaRepository<TrainerCard, Integer> {
}
