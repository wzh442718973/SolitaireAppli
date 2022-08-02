package com.example.solitaireappli.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.solitaireappli.model.Card;
import com.example.solitaireappli.model.Rank;
import com.example.solitaireappli.model.Suit;

public class CardLoader implements ICardLoader{

    public Bitmap cardLoad(Context context, Card card)
    {
        Bitmap bit;
        if(card.isReturned())
        {


            bit =  BitmapFactory.decodeResource(context.getResources(),context.getResources().getIdentifier("back",
                    "drawable", context.getPackageName()));


        }
        else
        {
            if(card.getRank() == Rank.NONE || card.getSuit() == Suit.NONE)
                bit =  BitmapFactory.decodeResource(context.getResources(),context.getResources().getIdentifier("empty",
                        "drawable", context.getPackageName()));
            else
                bit =  BitmapFactory.decodeResource(context.getResources(),context.getResources().getIdentifier("card" + card,
                        "drawable", context.getPackageName()));
        }
        return Bitmap.createBitmap(bit,0 ,0,bit.getWidth(),bit.getHeight());
    }

}