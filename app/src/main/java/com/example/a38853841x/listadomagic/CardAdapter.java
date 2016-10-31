package com.example.a38853841x.listadomagic;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Kamelot on 31/10/2016.
 */

public class CardAdapter extends ArrayAdapter<Carta> {

    public CardAdapter(Context context, int resource, List<Carta> objects){
        super(context, resource, objects);
    }
}
