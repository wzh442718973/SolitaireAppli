package com.example.solitaireappli.model;

public enum Rank {
    NONE(0),
    AS(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13);

    public final int value;



    @Override
    public String toString() {
        String str = ""+ value;
        if(value == 11)
            str = "j";
        if(value == 12)
            str = "q";
        if(value == 13)
            str = "k";
        if(value == 1)
            str = "a";
        return str;
    }

    private Rank(int value)
    {
        this.value =value;
    }

//    public boolean isJustHigher(Hauteur hauteur)
//    {
//        return this.value == hauteur.value + 1;
//    }


    public int getValue() {
        return value;
    }

}
