package com.example.a38853841x.listadomagic;

import android.content.Context;
import android.net.Uri;
import java.util.ArrayList;
import nl.littlerobots.cupboard.tools.provider.UriHelper;
import android.support.v4.content.CursorLoader;
import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by 38853841x on 18/11/16.
 */


public class DataManager {

    private static UriHelper URI_HELPER = UriHelper.with(MagicContentProvider.AUTHORITY);
    private static Uri CARD_URI = URI_HELPER.getUri(Carta.class);

    static void saveCards(ArrayList<Carta> cartas, Context context) {
            cupboard().withContext(context).put(CARD_URI, Carta.class, cartas);
    }

    static void deleteCards(Context context) {
                cupboard().withContext(context).delete(CARD_URI, "_id > ?", "1");

            }
    static CursorLoader getCursorLoader(Context context, String color, String rarity) {

        if ((rarity.equals("any"))&& (color.equals("Uncolor"))){

            return new CursorLoader(context, CARD_URI, null, null, null, null);
        }

        else if (rarity.equals("Basic Land")){

            String selectRarity = rarity != null ? "rarity = '" + rarity + "'" : null;
           // result = MagicApi.getCartesFilterRarity(rarity);
            return new CursorLoader(context, CARD_URI, null, selectRarity, null, null);

        }
        else if (!(rarity.equals("any")) && (color.equals("Uncolor"))){

            String selectRarity = rarity != null ? "rarity = '" + rarity + "'" : null;
            // result = MagicApi.getCartesFilterRarity(rarity);
            return new CursorLoader(context, CARD_URI, null, selectRarity, null, null);

        }

        else  if ((rarity.equals("any"))&& !(color.equals("Uncolor"))){

            String selectColor = rarity != null ? "color = '" + rarity + "'" : null;
            //result = MagicApi.getCartesFilterColor(color);
            return new CursorLoader(context, CARD_URI, null, selectColor, null, null);

        }

        else {
            String selectColor = rarity != null ? "color = '" + rarity + "'" : null;
            String selectRarity = rarity != null ? "rarity = '" + rarity + "'" : null;
            return new CursorLoader(context, CARD_URI, null, selectColor + " AND" + selectColor, null, null);

        }

    }
}
