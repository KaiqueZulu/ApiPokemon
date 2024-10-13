package com.matdev.ApiPokemon.model;

import com.matdev.ApiPokemon.enums.TrainerVariant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Entity
@Table(name = "TrainerCards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerCard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private TrainerVariant variant;
    private String about;
    private HashMap<String, String> cardEffectArea;
}
