package com.example.a38853841x.listadomagic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.example.a38853841x.listadomagic.databinding.FragmentMainBinding;
import java.util.ArrayList;
import com.alexvasilkov.events.Events;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private CardCursorAdapter adapter;
    private ProgressDialog dialog;

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

        FragmentMainBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_main, container, false);
                View view = binding.getRoot();

        adapter = new CardCursorAdapter(getContext(), Carta.class);

        dialog = new ProgressDialog(getContext());
        dialog.setMessage(("Loading"));

        binding.lvCards.setAdapter(adapter);
        binding.lvCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Carta carta = (Carta) adapterView.getItemAtPosition(i);

                if (!esTablet()) {
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    intent.putExtra("Carta", carta);


                    startActivity(intent);
                }
                else{
                    Events.create("card-selected").param(carta).post();
                }

            }
        });

        getLoaderManager().initLoader(0, null, this);

        return view;
    }

    @Events.Subscribe("Start-downloadinf-data")
    void preRefresh(){
        dialog.show();
    }

    @Events.Subscribe("finish-downloadin-data")
    void afterRefresh(){
        dialog.dismiss();
    }


    boolean esTablet() {
                return getResources().getBoolean(R.bool.tablet);
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
        Events.register(this);
    }

    private void refresh(){

        RefreshDataTask task = new RefreshDataTask(getActivity().getApplicationContext());
        task.execute();

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return DataManager.getCursorLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);

    }


}

