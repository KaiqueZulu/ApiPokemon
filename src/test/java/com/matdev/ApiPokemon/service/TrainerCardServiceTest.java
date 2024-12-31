package com.matdev.ApiPokemon.service;

import com.matdev.ApiPokemon.enums.TrainerVariant;
import com.matdev.ApiPokemon.exception.CardNotFoundException;
import com.matdev.ApiPokemon.model.TrainerCard;
import com.matdev.ApiPokemon.repository.TrainerCardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class TrainerCardServiceTest {

    @Mock
    private TrainerCardRepository repository;

    @InjectMocks
    private TrainerCardService service;

    private TrainerCard card;
    private TrainerCard savedCard;
    private List<TrainerCard> cards;
    private TrainerCard oldCard;
    private TrainerCard newCard;

    @BeforeEach
    public void setUp() {
        HashMap<String, String> cardEffectArea = new HashMap<>(1);
        cardEffectArea.put(
                "Action",
                "Swap your active Pokémon for 1 of your Benched Pokémon. If you do this, deal cards until you have 5 cards in your hand.");

        card = new TrainerCard(null, "Surfer", TrainerVariant.SUPPORTER, "", cardEffectArea);
        savedCard = new TrainerCard(1, "Surfer", TrainerVariant.SUPPORTER, "", cardEffectArea);
        cards = List.of(savedCard);

        oldCard = new TrainerCard(1, "Surfer", TrainerVariant.SUPPORTER, "", cardEffectArea);
        newCard = new TrainerCard(1, "updated", TrainerVariant.SUPPORTER, "", cardEffectArea);

    }

    @Test
    public void testCreateTrainerCard() {


        Mockito.when(repository.save(Mockito.any(TrainerCard.class))).thenReturn(savedCard);

        TrainerCard result = service.create(card);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(savedCard.getId(), result.getId());
    }

    @Test
    public void testGetAllTrainerCards() {


        Mockito.when(repository.findAll()).thenReturn(cards);

        List<TrainerCard> result = service.getAll();

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
    public void testUpdateTrainerCard() {
        int id = 1;


        Mockito.when(repository.findById(id)).thenReturn(Optional.of(oldCard));
        Mockito.when(repository.save(Mockito.any(TrainerCard.class))).thenReturn(newCard);

        TrainerCard result = service.update(1, newCard);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(newCard, result);
    }

    @Test
    public void testDeleteTrainerCard() {
        int id = 1;

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(card));
        Mockito.doNothing().when(repository).delete(card);

        String result = service.delete(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Card deleted successfully.", result);
    }
}