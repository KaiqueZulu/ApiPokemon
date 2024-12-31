package com.matdev.ApiPokemon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matdev.ApiPokemon.exception.CardNotFoundException;
import com.matdev.ApiPokemon.model.PokemonCard;
import com.matdev.ApiPokemon.service.PokemonCardService;
import com.matdev.ApiPokemon.utils.PokemonCardBuilder;
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
        PokemonCardBuilder builder = new PokemonCardBuilder();

        card = builder.build();
        savedCard = builder.withId(1).build();
        updateCard = builder.withLife(100).build();
        updatedCard = savedCard;

        pokemonCardList.add(card);
    }

    @Test
    public void testCreatePokemonCard() throws Exception {

        Mockito.when(service.create(Mockito.any(PokemonCard.class))).thenReturn(savedCard);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cards/pokemon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(card)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.life").exists())
                .andExpect(jsonPath("$.pokemon.id").exists())
                .andExpect(jsonPath("$.collectorInformation").exists());
    }

    @Test
    public void testGetAllPokemonCards() throws Exception{
        Mockito.when(service.getAll()).thenReturn(pokemonCardList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cards/pokemon"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].life").exists())
                .andExpect(jsonPath("$[0].pokemon.id").exists())
                .andExpect(jsonPath("$[0].collectorInformation").exists());
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
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.life").exists())
                .andExpect(jsonPath("$.pokemon.id").exists())
                .andExpect(jsonPath("$.collectorInformation").exists());
    }

    @Test
    public void testDeletePokemonCards() throws Exception{
        int id = 1;
        Mockito.when(service.delete(id)).thenReturn("Card deleted successfully.");

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/cards/pokemon/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("Card deleted successfully."));
    }
}
