package com.example.a38853841x.listadomagic;

import nl.littlerobots.cupboard.tools.provider.CupboardContentProvider;
import static nl.qbusict.cupboard.CupboardFactory.cupboard;


/**
 * Created by 38853841x on 15/11/16.
 */


public class MagicContentProvider extends CupboardContentProvider {


        // The content provider authority is used for building Uri's for the provider
        public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

        static {
            cupboard().register(Carta.class);
        }

        public MagicContentProvider() {
            super(AUTHORITY, 1);
        }
    }

