package com.matdev.ApiPokemon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matdev.ApiPokemon.enums.PokemonType;
import com.matdev.ApiPokemon.exception.CardNotFoundException;
import com.matdev.ApiPokemon.model.PokemonCard;
import com.matdev.ApiPokemon.service.PokemonCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PokemonCardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PokemonCardService service;

    private PokemonCard card;
    private PokemonCard savedCard;
    private final List<PokemonCard> pokemonCardList = new ArrayList<>();
    private PokemonCard updateCard;
    private PokemonCard updatedCard;


    @BeforeEach
    public void setUp() {
        // Criação de HashMaps comuns para o teste
        HashMap<String, String> abilities = new HashMap<>();

        HashMap<String, String> cardBody = new HashMap<>();
        cardBody.put("name", "Dust Collection");
        cardBody.put("damage", "20");
        cardBody.put("energyCost", PokemonType.NORMAL + ":1");

        HashMap<PokemonType, String> weaknesses = new HashMap<>();
        weaknesses.put(PokemonType.PSYCHIC, "x2");

        HashMap<PokemonType, String> resistances = new HashMap<>();

        HashMap<String, String> collectorInformation = new HashMap<>();
        collectorInformation.put("illustrator", "Sanosuke Sakuma");
        collectorInformation.put("collectionNumber", "65/149");
        collectorInformation.put("rarity", "Rare");

        // Instanciação dos objetos card e savedCard
        card = new PokemonCard(
                null,
                "Cosmog",
                60,
                PokemonType.PSYCHIC,
                "basic",
                "",
                "Cosmoem",
                0.2F,
                0.1F,
                abilities,
                cardBody,
                weaknesses,
                resistances,
                "1",
                collectorInformation,
                "Your body is gaseous and fragile. It grows slowly while collecting dust from the atmosphere."
        );

        savedCard = new PokemonCard(
                1, // ID preenchido
                card.getName(),
                card.getLife(),
                card.getType(),
                card.getStage(),
                card.getEvolvesFrom(),
                card.getEvolvesTo(),
                card.getHeight(),
                card.getWeight(),
                card.getAbility(),
                card.getCardBody(),
                card.getWeakness(),
                card.getResistance(),
                card.getRetreatValue(),
                card.getCollectorInformation(),
                card.getAbout()
        );

        pokemonCardList.add(card);

        updateCard = card;
        updatedCard = savedCard;
    }

    @Test
    public void testCreatePokemonCard() throws Exception {

        Mockito.when(service.create(Mockito.any(PokemonCard.class))).thenReturn(savedCard);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cards/pokemon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(card)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Cosmog"))
                .andExpect(jsonPath("$.type").value(PokemonType.PSYCHIC.toString()))
                .andExpect(jsonPath("$.evolvesTo").value("Cosmoem"))
                .andExpect(jsonPath("$.collectorInformation.illustrator").value("Sanosuke Sakuma"))
                .andExpect(jsonPath("$.about").value(card.getAbout()));
    }

    @Test
    public void testGetAllPokemonCards() throws Exception{
        Mockito.when(service.getAll()).thenReturn(pokemonCardList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cards/pokemon"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(card.getName()))
                .andExpect(jsonPath("$[0].type").value(card.getType().toString()));
    }

    @Test
    public void testGetByIdNotFound() throws Exception{
        int id = 1;

        Mockito.when(service.getById(id)).thenThrow(new CardNotFoundException("Card with ID " + id + " not found."));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cards/energy/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Card Not Found"))
                .andExpect(jsonPath("$.message").value("Card with ID 1 not found."));
    }

    @Test
    public void testUpdatePokemonCards() throws Exception {
        int id = 1;

        Mockito.when(service.update(Mockito.eq(id), Mockito.any(PokemonCard.class))).thenReturn(updatedCard);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/cards/pokemon/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateCard)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Cosmog"));
    }

    @Test
    public void testDeletePokemonCards() throws Exception{
        int id = 1;
        Mockito.doNothing().when(service).delete(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/cards/pokemon/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("Card deleted successfully."));
    }
}
