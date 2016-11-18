package com.example.a38853841x.listadomagic;

import android.content.Context;
import android.net.Uri;
import java.util.ArrayList;
import nl.littlerobots.cupboard.tools.provider.UriHelper;
import static nl.qbusict.cupboard.CupboardFactory.cupboard;
/**
 * Created by 38853841x on 18/11/16.
 */


public class DataMAnager {

    private static UriHelper URI_HELPER = UriHelper.with(MagicContentProvider.AUTHORITY);
        private static Uri MOVIE_URI = URI_HELPER.getUri(Carta.class);

                static void saveCards(ArrayList<Carta> movies, Context context) {
                cupboard().withContext(context).put(MOVIE_URI, Carta.class, movies);
            }
}
