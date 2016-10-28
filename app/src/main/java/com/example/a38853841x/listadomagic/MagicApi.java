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
    private final String Base_URL = "https://api.magicthegathering.io/v1/cards";

    ArrayList<Carta> getCartes() {
        Uri builtUri = Uri.parse(Base_URL)
            .buildUpon()
            .build();
        String url = builtUri.toString();
        return doCall(url);
    }

    ArrayList<Carta> getCartesDefault() {
        Uri builtUri = Uri.parse(Base_URL)
                .buildUpon()
                .appendPath("color")
                .build();
        String url = builtUri.toString();
        return doCall(url);
    }

    private ArrayList<Carta> doCall(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);
            return processJson(JsonResponse);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;

        }

    private ArrayList<Carta> processJson(String jsonResponse) {

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

                    cards.add(carta);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

               return cards;
    }
}





