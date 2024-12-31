package com.matdev.ApiPokemon.service;

import com.matdev.ApiPokemon.exception.PokemonNotFoundException;
import com.matdev.ApiPokemon.model.Pokemon;
import com.matdev.ApiPokemon.repository.PokemonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PokemonServiceTest {

    List<Pokemon> cards;
    Pokemon card;
    @Mock
    private PokemonRepository repository;
    @InjectMocks
    private PokemonService service;

    @BeforeEach
    public void setUp() {
        card = new Pokemon(1, "Bulbasaur", "Grass/Poison", "2′04″", 0.7f, 15.2f, 6.9f);


        cards = List.of(
                new Pokemon(1, "Bulbasaur", "Grass/Poison", "2′04″", 0.7f, 15.2f, 6.9f),
                new Pokemon(2, "Ivysaur", "Grass/Poison", "3′03″", 1.0f, 28.7f, 13.0f)
        );
    }

    @Test
    public void testCreateEnergyCard() {


        Mockito.when(repository.save(Mockito.any(Pokemon.class))).thenReturn(card);

        Pokemon result = service.create(card);

        Assertions.assertNotNull(result);
    }

    @Test
    public void testGetAllEnergyCards() {

        Mockito.when(repository.findAll()).thenReturn(cards);

        List<Pokemon> result = service.getAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(cards, result);
    }

    @Test
    public void testGetByIdNotFound() {
        int id = 1;

        Mockito.when(repository.findById(id)).thenThrow(new PokemonNotFoundException("Pokemon with ID " + id + " not found."));

        Assertions.assertThrows(PokemonNotFoundException.class, () -> service.getById(id));
    }
}