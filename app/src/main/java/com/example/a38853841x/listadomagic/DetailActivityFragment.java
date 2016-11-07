package com.example.a38853841x.listadomagic;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail, container, false);

        Intent i = getActivity().getIntent();

        if (i != null) {
            Carta carta = (Carta) i.getSerializableExtra("carta");

            if (carta != null) {
                updateUi(carta);
            }
        }

        return view;
    }

    private void updateUi(Carta carta) {
        Log.d("Carta", carta.toString());

        ivPosterImage = (ImageView) view.findViewById(R.id.ivPosterImage);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvType = (TextView) view.findViewById(R.id.tvType);
        tvRarity = (TextView) view.findViewById(R.id.tvRarity);
        tvColors = (TextView) view.findViewById(R.id.tvColors);

    }
}
