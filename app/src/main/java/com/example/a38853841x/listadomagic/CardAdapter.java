package com.example.a38853841x.listadomagic;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a38853841x.listadomagic.databinding.LvCardsRowBinding;

import java.util.List;

/**
 * Created by Kamelot on 31/10/2016.
 */

public class CardAdapter extends ArrayAdapter<Carta> {

    public CardAdapter(Context context, int resource, List<Carta> objects){
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Obtenim l'objecte en la possició corresponent
        Carta card = getItem(position);
        Log.w("XXXX", card.toString());

        LvCardsRowBinding binding = null;

        // Mirem a veure si la View s'està reusant, si no es així "inflem" la View
        // https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView#row-view-recycling
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            binding = DataBindingUtil.inflate(inflater, R.layout.lv_cards_row, parent, false);
        }

        else {

            binding = DataBindingUtil.getBinding(convertView);
        }


        // Fiquem les dades dels objectes (provinents del JSON) en el layout
        binding.tvCard.setText(card.getTitle());
        binding.tvRarity.setText("Rarity: " + card.getRarity());
        binding.tvColors.setText("Colors: " + card.getColors().replace("[","").replace("\"","").replace("]",""));
        binding.tvType.setText("Type: " + card.getType());
        Glide.with(getContext()).load(card.getImageUrl()).into(binding.ivPosterImage);


        // Retornem la View replena per a mostrarla
        return binding.getRoot();
    }
}
