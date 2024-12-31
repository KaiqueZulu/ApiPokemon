package com.matdev.ApiPokemon.service;

import com.matdev.ApiPokemon.exception.CardNotFoundException;
import com.matdev.ApiPokemon.model.PokemonCard;
import com.matdev.ApiPokemon.repository.PokemonCardRepository;
import com.matdev.ApiPokemon.repository.PokemonRepository;
import com.matdev.ApiPokemon.utils.PokemonCardBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PokemonCardServiceTest {

    private final List<PokemonCard> pokemonCardList = new ArrayList<>();

    @Mock
    private PokemonCardRepository repository;

    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private PokemonCardService service;

    private PokemonCard card;
    private PokemonCard savedCard;
    private PokemonCard oldCard;
    private PokemonCard newCard;


    @BeforeEach
    public void setUp() {
        PokemonCardBuilder builder = new PokemonCardBuilder();

        card = builder.build();
        savedCard = builder.withId(1).build();
        oldCard = builder.withLife(100).build();
        newCard = savedCard;

        pokemonCardList.add(card);
    }

    @Test
    public void testCreatePokemonCard() {

        Mockito.when(pokemonRepository.findById(card.getPokemon().getId()))
                .thenReturn(Optional.of(card.getPokemon()));
        Mockito.when(repository.save(Mockito.any(PokemonCard.class))).thenReturn(savedCard);

        PokemonCard result = service.create(card);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(savedCard.getId(), result.getId());
    }

    @Test
    public void testGetAllPokemonCards() {
        Mockito.when(repository.findAll()).thenReturn(pokemonCardList);

        List<PokemonCard> result = service.getAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(pokemonCardList, result);
    }

    @Test
    public void testGetByIdNotFound() {
        int id = 1;

        Mockito.when(repository.findById(id)).thenThrow(new CardNotFoundException("Card with ID " + id + " not found."));

        Assertions.assertThrows(CardNotFoundException.class, () -> service.getById(id));
    }

    @Test
    public void testUpdatePokemonCards() {
        int id = 1;

        Mockito.when(repository.findById(id)).thenReturn(Optional.ofNullable(oldCard));
        Mockito.when(repository.save(oldCard)).thenReturn(newCard);

        PokemonCard result = service.update(id, oldCard);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(newCard, result);
    }

    @Test
    public void testDeletePokemonCards() {
        int id = 1;

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(card));
        Mockito.doNothing().when(repository).delete(card);

        String result = service.delete(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Card deleted successfully.", result);
    }
}
