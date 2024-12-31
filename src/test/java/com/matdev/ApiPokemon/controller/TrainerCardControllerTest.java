package com.matdev.ApiPokemon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matdev.ApiPokemon.enums.TrainerVariant;
import com.matdev.ApiPokemon.exception.CardNotFoundException;
import com.matdev.ApiPokemon.model.TrainerCard;
import com.matdev.ApiPokemon.service.TrainerCardService;
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

import java.util.HashMap;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TrainerCardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TrainerCardService service;


    private TrainerCard card;
    private List<TrainerCard> cards;

    @BeforeEach
    public void setUp() {
        HashMap<String, String> cardEffectArea = new HashMap<>(1);
        cardEffectArea.put(
                "Action",
                "Swap your active Pokémon for 1 of your Benched Pokémon. If you do this, deal cards until you have 5 cards in your hand.");

        card = new TrainerCard(1, "Surfer", TrainerVariant.SUPPORTER, "", cardEffectArea);

        cards = List.of(
                new TrainerCard(1, "Surfer", TrainerVariant.SUPPORTER, "", cardEffectArea)
        );
    }

    @Test
    public void testCreateTrainerCard() throws Exception {

        Mockito.when(service.create(Mockito.any(TrainerCard.class))).thenReturn(card);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cards/trainer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(card)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.variant").exists())
                .andExpect(jsonPath("$.about").exists())
                .andExpect(jsonPath("$.cardEffectArea").exists());
    }

    @Test
    public void testGetAllTrainerCards() throws Exception {


        Mockito.when(service.getAll()).thenReturn(cards);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cards/trainer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").exists())
                .andExpect(jsonPath("$[0].variant").exists())
                .andExpect(jsonPath("$[0].about").exists())
                .andExpect(jsonPath("$[0].cardEffectArea").exists());
    }

    @Test
    public void testGetByIdNotFound() throws Exception {
        int id = 1;

        Mockito.when(service.getById(id)).thenThrow(new CardNotFoundException("Card with ID " + id + " not found."));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cards/trainer/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Card Not Found"))
                .andExpect(jsonPath("$.message").value("Card with ID " + id + " not found."));
    }

    @Test
    public void testUpdateTrainerCard() throws Exception {
        int id = 1;

        Mockito.when(service.update(Mockito.eq(id), Mockito.any(TrainerCard.class))).thenReturn(card);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/cards/trainer/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(card)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.variant").exists())
                .andExpect(jsonPath("$.about").exists())
                .andExpect(jsonPath("$.cardEffectArea").exists());
    }

    @Test
    public void testDeleteTrainerCard() throws Exception {
        int id = 1;

        Mockito.when(service.delete(id)).thenReturn("Card deleted successfully.");

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/cards/trainer/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("Card deleted successfully."));
    }
}
