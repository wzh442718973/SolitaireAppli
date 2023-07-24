package com.example.solitaireappli;

import android.app.Application;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.example.solitaireappli.view.ICardParameters;
import com.example.solitaireappli.view.ISetParameters;

public class App extends Application implements ICardParameters {//}, ISetParameters {

    public static final int DESCK_COUNT = 7;


    public static float X_DECK, Y_DECK; //deckView

    public static float xPioche = 10.f, yPioche = 10.f; //hideView

    public static float xDefausse = 100.f, yDefausse = 10.f;//showView

    public static float xPiles = 280.f, yPiles = 10.f;//stackView

    public static float H_SPACE = 10, V_SPACE = 5;

    public static float CARD_WIDTH, CARD_HEIGH = -1;

    public static float S_SPACE = 8;

    @Override
    public void onCreate() {
        super.onCreate();

        final Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();

        H_SPACE = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, dm); //left, right
        V_SPACE = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, dm);

        S_SPACE = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, dm);

        CARD_WIDTH = (dm.widthPixels - DESCK_COUNT * H_SPACE * 2) / DESCK_COUNT;
        CARD_HEIGH = CARD_WIDTH * 1.45f;


        xPioche = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, dm);
        xDefausse = xPioche + CARD_WIDTH + 15;////TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, dm);
        xPiles = dm.widthPixels - (CARD_WIDTH + H_SPACE * 2) * 4;//TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 280, dm);

        yPiles = yDefausse = yPioche = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, dm);

        //TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, dm);




        X_DECK = 0;//TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, dm);
        Y_DECK = yPioche + CARD_HEIGH + TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, dm);

//        HEAD_WIDTH = (dm.widthPixels - )

    }

    @Override
    public int getWidth() {
        return (int) CARD_WIDTH;
    }

    @Override
    public int getHeight() {
        return (int) CARD_HEIGH;
    }

//    @Override
//    public float getxPioche() {
//        return 0;
//    }
//
//    @Override
//    public float getyPioche() {
//        return 0;
//    }
//
//    @Override
//    public float getxDefausse() {
//        return 0;
//    }
//
//    @Override
//    public float getyDefausse() {
//        return 0;
//    }
//
//    @Override
//    public float getxPiles() {
//        return 0;
//    }
//
//    @Override
//    public float getyPiles() {
//        return 0;
//    }
//
//    @Override
//    public float getdXPiles() {
//        return 0;
//    }
//
//    @Override
//    public float getdYPiles() {
//        return 0;
//    }
//
//    @Override
//    public float getxDeck() {
//        return 0;
//    }
//
//    @Override
//    public float getyDeck() {
//        return 0;
//    }
//
//    @Override
//    public float getdXDeck() {
//        return 0;
//    }
//
//    @Override
//    public float getdYDeck() {
//        return 0;
//    }
//
//    @Override
//    public float getxWin() {
//        return 0;
//    }
//
//    @Override
//    public float getyWin() {
//        return 0;
//    }
}
