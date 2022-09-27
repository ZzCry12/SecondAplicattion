package com.example.secondaplicattion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class PokemonApi {
    ArrayList<Pokemon> getPokemons() {
        String url = "https://pokeapi.co/api/v2/pokemon";

        try {
            String result = HttpUtils.get(url);

            JSONObject jsonResult = new JSONObject(result);

            JSONArray results = jsonResult.getJSONArray("results");

            ArrayList<Pokemon> pokemons = new ArrayList<>();

            for (int i = 0; i < results.length(); i++) {
                JSONObject pokemonJson = results.getJSONObject(i);

                Pokemon pokemon = new Pokemon();
                pokemon.setName(pokemonJson.getString("name"));
                pokemon.setDetailsUrl(pokemonJson.getString("url"));

                String resultDetails = HttpUtils.get(pokemon.getDetailsUrl());
                JSONObject jsonDetails = new JSONObject(resultDetails);

                pokemon.setHeight(jsonDetails.getInt("height"));
                pokemon.setWeight(jsonDetails.getInt("Weight"));
                pokemon.setImage(jsonDetails.getString(url));

                pokemons.add(pokemon);
            }

            return pokemons;
        } catch (IOException | JSONException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
