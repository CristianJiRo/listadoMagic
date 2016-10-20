package com.example.a38853841x.listadomagic;

import android.net.Uri;

import java.io.IOException;
import java.net.URI;

/**
 * Created by Kamelot on 20/10/2016.
 */

public class MagicApi {
    private final String Base_URL = "https://api.magicthegathering.io/v1/cards";

    String getCartes() {
        Uri builtUri = Uri.parse(Base_URL)
            .buildUpon()
            .appendPath("name")
            .appendPath("imageUrl")
            .appendPath("type")
            .build();
        String url = builtUri.toString();
        try {
            String JsonResponse = HttpUtils.get(url);
            return JsonResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}


