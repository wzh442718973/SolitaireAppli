package com.example.solitaireappli.view;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.solitaireappli.model.Card;

public interface ICardLoader {
    public Bitmap cardLoad(Context context, Card card);
}
