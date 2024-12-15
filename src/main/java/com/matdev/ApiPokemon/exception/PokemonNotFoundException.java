package com.matdev.ApiPokemon.exception;

public class PokemonNotFoundException extends RuntimeException {
    public PokemonNotFoundException(String message) {
        super(message);
    }
}
