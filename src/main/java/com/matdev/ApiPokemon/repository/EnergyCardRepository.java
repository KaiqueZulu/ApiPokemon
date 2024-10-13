package com.matdev.ApiPokemon.repository;

import com.matdev.ApiPokemon.model.EnergyCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnergyCardRepository extends JpaRepository<EnergyCard, Integer> {
}
