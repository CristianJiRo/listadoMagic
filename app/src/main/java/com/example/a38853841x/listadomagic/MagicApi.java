package com.example.a38853841x.listadomagic;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

/**
 * Created by Kamelot on 20/10/2016.
 */

class MagicApi {
    static final String Base_URL = "https://api.magicthegathering.io/v1/cards";

    static ArrayList<Carta> getCartes() {
        Uri builtUri = Uri.parse(Base_URL)
            .buildUpon()
            .build();
        String url = builtUri.toString();
        return doCall(url);
    }


    static ArrayList<Carta> getCartesFilterRarity (String rarity) {
        Uri builtUri = Uri.parse(Base_URL)
                .buildUpon()
                .appendQueryParameter("rarity", rarity)
                .build();
        String url = builtUri.toString();
        return doCall(url);
    }

    static ArrayList<Carta> getCartesFilterColor (String colors) {
        Uri builtUri = Uri.parse(Base_URL)
                .buildUpon()
                .appendQueryParameter("colors", colors)
                .build();
        String url = builtUri.toString();
        return doCall(url);
    }

    static ArrayList<Carta> getCartesFilters (String colors, String rarity) {
        Uri builtUri = Uri.parse(Base_URL)
                .buildUpon()
                .appendQueryParameter("colors", colors)
                .appendQueryParameter("rarity", rarity)
                .build();
        String url = builtUri.toString();
        return doCall(url);
    }


    static ArrayList<Carta> doCall(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);
            return processJson(JsonResponse);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;

        }

    static ArrayList<Carta> processJson(String jsonResponse) {

        ArrayList<Carta> cards = new ArrayList<>();
            try {
                JSONObject data = new JSONObject(jsonResponse);
                JSONArray jsonCartas = data.getJSONArray("cards");
                for (int i = 0; i < jsonCartas.length(); i++) {

                    JSONObject jsonCarta = jsonCartas.getJSONObject(i);

                    Carta carta = new Carta();
                    carta.setTitle(jsonCarta.getString("name"));
                    carta.setType(jsonCarta.getString("type"));
                    carta.setImageUrl(jsonCarta.getString("imageUrl"));
                    carta.setRarity(jsonCarta.getString("rarity"));

                    if (jsonCarta.isNull("colors")){
                        carta.setColors("Uncolor");
                    }
                    else{
                        carta.setColors(jsonCarta.getString("colors"));
                    }

                    cards.add(carta);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

               return cards;
    }
}





