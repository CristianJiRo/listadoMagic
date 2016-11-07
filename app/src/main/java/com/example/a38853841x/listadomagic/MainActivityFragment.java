package com.example.a38853841x.listadomagic;

import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ArrayList<Carta> items;
    private CardAdapter adapter;

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

        items = new ArrayList<>();
        adapter = new CardAdapter(
                getContext(),
                R.layout.lv_cards_row,
                items
        );

        lvCards.setAdapter(adapter);
        lvCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Carta carta = (Carta) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("Carta", carta);
                startActivity(intent);
            }
        });

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

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }

    private void refresh(){

        RefreshDataTask task = new RefreshDataTask();
        task.execute();

    }

    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<Carta>> {

        @Override
        protected ArrayList<Carta> doInBackground(Void... voids) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            String color = preferences.getString("colors", "Uncolor");
            String rarity = preferences.getString("rarity", "any");

            MagicApi api = new MagicApi();
            ArrayList<Carta> result;

            if ((rarity.equals("any"))&& (color.equals("Uncolor"))){

                result = api.getCartes();
            }

            else if (rarity.equals("Basic Land")){

                result = api.getCartesFilterRarity(rarity);

            }
            else if (!(rarity.equals("any")) && (color.equals("Uncolor"))){

                result = api.getCartesFilterRarity(rarity);

            }

            else  if ((rarity.equals("any"))&& !(color.equals("Uncolor"))){

                result = api.getCartesFilterColor(color);

            }

            else {
                result = api.getCartesFilters(color, rarity);

            }

            Log.d("DEBUG", result.toString());

            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Carta> cartas) {
            adapter.clear();
            for (Carta card : cartas){
                //adapter.add(card.getTitle());
                adapter.add(card);
            }
        }
    }

}

