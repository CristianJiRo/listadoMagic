package com.example.a38853841x.listadomagic;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import com.alexvasilkov.events.Events;
import java.util.ArrayList;

/**
 * Created by Kamelot on 07/12/2016.
 */

class RefreshDataTask extends AsyncTask<Void, Void, Void> {
    private Context context;

    RefreshDataTask(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Events.post("start-downloading-data");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Events.post("finish-downloading-data");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String color = preferences.getString("colors", "Uncolor");
        String rarity = preferences.getString("rarity", "any");

        ArrayList<Carta> result;

        if ((rarity.equals("any"))&& (color.equals("Uncolor"))){

            result = MagicApi.getCartes();
        }

        else if (rarity.equals("Basic Land")){

            result = MagicApi.getCartesFilterRarity(rarity);

        }
        else if (!(rarity.equals("any")) && (color.equals("Uncolor"))){

            result = MagicApi.getCartesFilterRarity(rarity);

        }

        else  if ((rarity.equals("any"))&& !(color.equals("Uncolor"))){

            result = MagicApi.getCartesFilterColor(color);

        }

        else {
            result = MagicApi.getCartesFilters(color, rarity);

        }

        Log.d("DEBUG", result.toString());

        DataManager.deleteCards(context);
        DataManager.saveCards(result, context);

        return null;
    }
}