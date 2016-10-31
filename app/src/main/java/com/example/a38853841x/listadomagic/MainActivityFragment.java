package com.example.a38853841x.listadomagic;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    public MainActivityFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView lvCards = (ListView) view.findViewById(R.id.lvCards);

    //    items = new ArrayList<>();


        String[] data = {

                "Angel Serra",
                "Sol Ring",
                "Royal Assasin",
                "Mithra Factory",
                "Cocatrize",
                "Force of Nature",
                "Counter Spell"

        };


        items = new ArrayList<>(Arrays.asList(data));
        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.lv_cards_row,
                R.id.tvCard,
                items
        );


        lvCards.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_cartas_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_refresh){
            refresh();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    private void refresh(){

        RefreshDataTask task = new RefreshDataTask();
        task.execute();

    }

    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<Carta>> {

        @Override
        protected ArrayList<Carta> doInBackground(Void... voids) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            String color = preferences.getString("color", "any");
            String rarity = preferences.getString("rarity", "any");

            MagicApi api = new MagicApi();
            ArrayList<Carta> result = null;

            if ((rarity.equals("any"))&& (color.equals("any"))){

                result = api.getCartes();
            }
            else {

                result = api.getCartes();
            }

            Log.d("DEBUG", result.toString());

            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Carta> cartas) {
            adapter.clear();
            for (Carta card : cartas){
                adapter.add(card.getTitle());
            }
        }
    }

}

