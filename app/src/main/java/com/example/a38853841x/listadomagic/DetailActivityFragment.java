package com.example.a38853841x.listadomagic;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gifbitmap.GifBitmapWrapper;
import com.example.a38853841x.listadomagic.databinding.FragmentDetailBinding;

/**
 * A placeholder fragment containing a simple view.
 */

public class DetailActivityFragment extends Fragment {

    private View view;
    private ImageView ivPosterImage;
    private TextView tvTitle;
    private TextView tvType;
    private TextView tvRarity;
    private TextView tvColors;
    private FragmentDetailBinding binding;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //view = inflater.inflate(R.layout.fragment_detail, container, false);

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

        /*ivPosterImage = (ImageView) view.findViewById(R.id.ivPosterImage);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvType = (TextView) view.findViewById(R.id.tvType);
        tvRarity = (TextView) view.findViewById(R.id.tvRarity);
        tvColors = (TextView) view.findViewById(R.id.tvColors);

        tvTitle.setText(carta.getTitle());
        tvType.setText(carta.getType());
        tvRarity.setText(carta.getRarity());
        tvColors.setText(carta.getColors().replace("[\"","").replace("\"]",""));
        Glide.with(getContext()).load(carta.getImageUrl()).into(ivPosterImage);
        */

        binding.tvTitle.setText(carta.getTitle());
        binding.tvType.setText(carta.getType());
        binding.tvRarity.setText(carta.getRarity());
        binding.tvColors.setText(carta.getColors());
        Glide.with(getContext()).load(carta.getImageUrl()).into(binding.ivPosterImage);





    }
}
