package com.matdev.ApiPokemon.utils;

import com.matdev.ApiPokemon.model.Pokemon;
import com.matdev.ApiPokemon.model.PokemonCard;

import java.util.HashMap;

public class PokemonCardBuilder {
    private Integer id = null;
    private Integer life = 120;
    private final Pokemon pokemon = new Pokemon(530);
    private final HashMap<String, String> ability = new HashMap<>();
    private final HashMap<String, String> cardBody = new HashMap<>();
    private final HashMap<String, String> collectorInformation = new HashMap<>();
    private String exceptionalType = "";
    private final HashMap<String, String> exceptionalRule = new HashMap<>();
    private String about = "Your drill has evolved into steel and is strong enough to drill through iron plates. This Pokémon is very useful in tunnel construction.";

    public PokemonCardBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public PokemonCardBuilder withLife(Integer life){
        this.life = life;
        return this;
    }

    public PokemonCard build(){
        return new PokemonCard(id, life, pokemon, ability, cardBody, collectorInformation, exceptionalType, exceptionalRule, about);
    }

}
