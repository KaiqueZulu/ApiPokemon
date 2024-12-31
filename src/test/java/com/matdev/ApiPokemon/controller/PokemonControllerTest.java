package com.matdev.ApiPokemon.controller;

import com.matdev.ApiPokemon.exception.PokemonNotFoundException;
import com.matdev.ApiPokemon.model.Pokemon;
import com.matdev.ApiPokemon.service.PokemonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PokemonControllerTest {
    List<Pokemon> cards;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PokemonService service;

    @BeforeEach
    public void setUp() {
        cards = List.of(
                new Pokemon(1, "Bulbasaur", "Grass/Poison", "2′04″", 0.7f, 15.2f, 6.9f),
                new Pokemon(2, "Ivysaur", "Grass/Poison", "3′03″", 1.0f, 28.7f, 13.0f)
        );
    }

    @Test
    public void testGetAllPokemon() throws Exception {


        Mockito.when(service.getAll()).thenReturn(cards);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pokemon"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[1].name").exists())
                .andExpect(jsonPath("$[0].type").exists())
                .andExpect(jsonPath("$[1].heightFt").exists())
                .andExpect(jsonPath("$[0].heightM").exists())
                .andExpect(jsonPath("$[0].weightLbs").exists())
                .andExpect(jsonPath("$[1].weightKgs").exists());
    }

    @Test
    public void testGetByIdNotFound() throws Exception {
        int id = 1;

        Mockito.when(service.getById(id)).thenThrow(new PokemonNotFoundException("Pokemon with ID " + id + " not found."));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pokemon/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Card Not Found"))
                .andExpect(jsonPath("$.message").value("Pokemon with ID " + id + " not found."));
    }

}
