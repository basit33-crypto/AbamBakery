package com.example.abambakery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class promo {
    String name;;
    int image;

    public promo( int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}