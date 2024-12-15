package com.matdev.ApiPokemon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DatabaseInitializer implements ApplicationRunner {

    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private CsvService csvService;

    @Value("${app.database.initialize:true}")
    private boolean initializeDatabase;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (!initializeDatabase) {
            System.out.println("Database initialization skipped.");
            return;
        }

        try {
            if (isPokedexEmpty() || isPokedexOutdated()) {
                System.out.println("Pokédex not found in database. Importing data...");
                csvService.importPokemonDataFromCsv();
                System.out.println("Pokédex imported successfully!");
            } else {
                System.out.println("Pokédex already exists in database. Skipping import.");
            }
        } catch (Exception e) {
            System.err.println("Error during database initialization: " + e.getMessage());
        }
    }

    private boolean isPokedexEmpty() {
        return pokemonService.count() == 0;
    }

    private boolean isPokedexOutdated() throws IOException {
        long dbCount = pokemonService.count();
        long csvCount = csvService.readLines().size();
        return dbCount < csvCount;
    }
}
