package com.example.a38853841x.listadomagic;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.example.a38853841x.listadomagic.databinding.FragmentDetailBinding;

/**
 * A placeholder fragment containing a simple view.
 */

public class DetailActivityFragment extends Fragment {

    private FragmentDetailBinding binding;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                                inflater, R.layout.fragment_detail, container, false);
                View view = binding.getRoot();

        Intent i = getActivity().getIntent();

        if (i != null) {
            Carta carta = (Carta) i.getSerializableExtra("Carta");

            if (carta != null) {
                updateUi(carta);
            }
        }

        return view;
    }

    private void updateUi(Carta carta) {
        Log.d("Carta", carta.toString());

        binding.tvTitle.setText(carta.getTitle());
        binding.tvType.setText(carta.getType());
        binding.tvRarity.setText(carta.getRarity().replace("[\"","").replace("\"]",""));
        binding.tvColors.setText(carta.getColors().replace("[\"","").replace("\"]",""));
        Glide.with(getContext()).load(carta.getImageUrl()).into(binding.ivPosterImage);

    }
}
