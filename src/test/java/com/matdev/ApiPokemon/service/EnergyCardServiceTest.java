package com.matdev.ApiPokemon.service;

import com.matdev.ApiPokemon.enums.PokemonType;
import com.matdev.ApiPokemon.exception.CardNotFoundException;
import com.matdev.ApiPokemon.model.EnergyCard;
import com.matdev.ApiPokemon.repository.EnergyCardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class EnergyCardServiceTest {

    @Mock
    private EnergyCardRepository repository;

    @InjectMocks
    private EnergyCardService service;

    @Test
    public void testCreateEnergyCard() {
        EnergyCard card = new EnergyCard(null, "Energy", PokemonType.WATER, "");
        EnergyCard savedCard = new EnergyCard(1, "Energy", PokemonType.WATER, "");

        Mockito.when(repository.save(Mockito.any(EnergyCard.class))).thenReturn(savedCard);

        EnergyCard result = service.create(card);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(savedCard.getId(), result.getId());
    }

    @Test
    public void testGetAllEnergyCards() {
        List<EnergyCard> cards = List.of(
                new EnergyCard(1, "Fire Card", PokemonType.FIRE, "Fiery power"),
                new EnergyCard(2, "Water Card", PokemonType.WATER, "Watery power")
        );

        Mockito.when(repository.findAll()).thenReturn(cards);

        List<EnergyCard> result = service.getAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(cards, result);
    }

    @Test
    public void testGetByIdNotFound() {
        int id = 1;

        Mockito.when(repository.findById(id)).thenThrow(new CardNotFoundException("Card with ID " + id + " not found."));

        Assertions.assertThrows(CardNotFoundException.class, () -> service.getById(id));
    }

    @Test
    public void testUpdateEnergyCard() {
        int id = 1;
        EnergyCard oldCard = new EnergyCard(1, "Electric Card", PokemonType.ELECTRIC, "");
        EnergyCard newCard = new EnergyCard(1, "Energy", PokemonType.WATER, "Updated description");

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(oldCard));
        Mockito.when(repository.save(Mockito.any(EnergyCard.class))).thenReturn(newCard);

        EnergyCard result = service.update(1, newCard);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(newCard, result);
    }

    @Test
    public void testDeleteEnergyCard() {
        EnergyCard card = new EnergyCard(1, "Energy", PokemonType.WATER, "");
        int id = 1;

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(card));
        Mockito.doNothing().when(repository).delete(card);

        String result = service.delete(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Card deleted successfully.", result);
    }
}