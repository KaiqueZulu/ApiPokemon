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
        EnergyCard card = new EnergyCard(null, "Energy", PokemonType.WATER, "");
        EnergyCard savedCard = new EnergyCard(1, "Energy", PokemonType.WATER, "");

        Mockito.when(service.create(Mockito.any(EnergyCard.class))).thenReturn(savedCard);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cards/energy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(card)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Energy"))
                .andExpect(jsonPath("$.type").value("WATER"));
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
                .andExpect(jsonPath("$[0].name").value("Fire Card"))
                .andExpect(jsonPath("$[1].name").value("Water Card"));
    }

    @Test
    public void testGetByIdNotFound() throws Exception {
        int id = 1;

        Mockito.when(service.getById(id)).thenThrow(new CardNotFoundException("Card with ID " + id + " not found."));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cards/energy/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Card Not Found"))
                .andExpect(jsonPath("$.message").value("Card with ID 1 not found."));
    }

    @Test
    public void testUpdateEnergyCard() throws Exception {
        int id = 1;
        EnergyCard updateCard = new EnergyCard(null, "Electric Card", PokemonType.ELECTRIC, "Updated description");
        EnergyCard updatedCard = new EnergyCard(1, "Electric Card", PokemonType.ELECTRIC, "Updated description");

        Mockito.when(service.update(Mockito.eq(id), Mockito.any(EnergyCard.class))).thenReturn(updatedCard);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/cards/energy/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateCard)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Electric Card"))
                .andExpect(jsonPath("$.type").value("ELECTRIC"));
    }

    @Test
    public void testDeleteEnergyCard() throws Exception {
        int id = 1;
        Mockito.doNothing().when(service).delete(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/cards/energy/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("Card deleted successfully."));
    }
}
