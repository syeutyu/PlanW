package com.example.user.junier;

import android.graphics.drawable.Drawable;

public class list_Item {
    private Drawable image;
    private String text;


    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
