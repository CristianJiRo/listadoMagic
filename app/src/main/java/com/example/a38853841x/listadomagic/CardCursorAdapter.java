package com.example.a38853841x.listadomagic;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import com.example.a38853841x.listadomagic.databinding.LvCardsRowBinding;

public class CardCursorAdapter extends CupboardCursorAdapter<Carta> {
    public CardCursorAdapter(Context context, Class<Carta> entityClass) {
        super(context, entityClass);
    }

    @Override
    public View newView(Context context, Carta model, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        LvCardsRowBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.lv_cards_row, parent, false);

        return binding.getRoot();
    }

    @Override
    public void bindView(View view, Context context, Carta carta) {
        LvCardsRowBinding binding = DataBindingUtil.getBinding(view);

        binding.tvTitle.setText(carta.getTitle());
        binding.tvType.setText(carta.getType());
        binding.tvRarity.setText(carta.getRarity().replace("[\"","").replace("\"]",""));
        binding.tvColors.setText(carta.getColors().replace("[\"","").replace("\"]",""));
        Glide.with(context).load(carta.getImageUrl()).into(binding.ivPosterImage);

    }
}