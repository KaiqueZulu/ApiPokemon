package com.matdev.ApiPokemon.service;

import com.matdev.ApiPokemon.model.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Service
public class CsvService {
    private static final String RESOURCES = "src/main/resources/data/";
    @Autowired
    private PokemonService pokemonService;

    public void importPokemonDataFromCsv() throws IOException {

        List<String> lines = readLines();

        for (final String line : lines) {
            String[] columns = line.split(",");
            Pokemon pokemon = createPokemonInstance(columns);
            pokemonService.create(pokemon);
        }
    }

    private Pokemon createPokemonInstance(String[] columns) {
        int id = Integer.parseInt(columns[0]);
        String name = columns[1];
        String type = columns[2];
        String heightFt = columns[3];
        float heightM = Float.parseFloat(columns[4]);
        float weightLbs = Float.parseFloat(columns[5]);
        float weightKgs = Float.parseFloat(columns[6]);

        return new Pokemon(id, name, type, heightFt, heightM, weightLbs, weightKgs);
    }

    public List<String> readLines() throws IOException {
        final Path path = Paths.get(RESOURCES + "Pokedex.csv");
        return Files.readAllLines(path);
    }
}
