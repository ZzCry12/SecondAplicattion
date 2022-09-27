package com.example.secondaplicattion;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class PokemonApi {
    void getPokemons() {
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

                pokemons.add(pokemon);
            }

            Log.e("XXX POKEMONS XXX", pokemons.toString());
        } catch (IOException | JSONException ex) {
            ex.printStackTrace();
        }
    }
}
