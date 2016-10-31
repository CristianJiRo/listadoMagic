package com.example.a38853841x.listadomagic;

import android.support.design.widget.Snackbar;

import java.io.Serializable;
import java.security.PrivateKey;

/**
 * Created by Kamelot on 20/10/2016.
 */

public class Carta implements Serializable {

    private String title;
    private String type;
    private String imageUrl;
    private String rarity;
    private String colors;

    public String getColors() { return colors; }

    public void setColors(String colors) { this.colors = colors; }

    public String getRarity() { return rarity; }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Carta{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", rarity='" + rarity + '\'' +
                ", colors='" + colors + '\'' +
                '}';
    }

}
