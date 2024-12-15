package com.matdev.ApiPokemon.repository;

import com.matdev.ApiPokemon.model.EnergyCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnergyCardRepository extends JpaRepository<EnergyCard, Integer> {
}
