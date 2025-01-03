package com.matdev.ApiPokemon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matdev.ApiPokemon.enums.PokemonType;
import com.matdev.ApiPokemon.exception.CardNotFoundException;
import com.matdev.ApiPokemon.model.EnergyCard;
import com.matdev.ApiPokemon.service.EnergyCardService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EnergyCardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EnergyCardService service;

    @Test
    public void testCreateEnergyCard() throws Exception {
        EnergyCard card = new EnergyCard(1, "Energy", PokemonType.WATER, "");

        Mockito.when(service.create(Mockito.any(EnergyCard.class))).thenReturn(card);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cards/energy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(card)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.type").exists())
                .andExpect(jsonPath("$.about").exists());
    }

    @Test
    public void testGetAllEnergyCards() throws Exception {
        List<EnergyCard> cards = List.of(
                new EnergyCard(1, "Fire Card", PokemonType.FIRE, "Fiery power"),
                new EnergyCard(2, "Water Card", PokemonType.WATER, "Watery power")
        );

        Mockito.when(service.getAll()).thenReturn(cards);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cards/energy"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[1].name").exists())
                .andExpect(jsonPath("$[0].type").exists())
                .andExpect(jsonPath("$[1].about").exists());
    }

    @Test
    public void testGetByIdNotFound() throws Exception {
        int id = 1;

        Mockito.when(service.getById(id)).thenThrow(new CardNotFoundException("Card with ID " + id + " not found."));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cards/energy/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Card Not Found"))
                .andExpect(jsonPath("$.message").value("Card with ID " + id + " not found."));
    }

    @Test
    public void testUpdateEnergyCard() throws Exception {
        int id = 1;
        EnergyCard card = new EnergyCard(1, "Electric Card", PokemonType.ELECTRIC, "Updated description");

        Mockito.when(service.update(Mockito.eq(id), Mockito.any(EnergyCard.class))).thenReturn(card);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/cards/energy/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(card)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.type").exists())
                .andExpect(jsonPath("$.about").exists());
    }

    @Test
    public void testDeleteEnergyCard() throws Exception {
        int id = 1;

        Mockito.when(service.delete(id)).thenReturn("Card deleted successfully.");

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/cards/energy/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("Card deleted successfully."));
    }
}
