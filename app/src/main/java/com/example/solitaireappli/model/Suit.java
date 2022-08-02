package com.example.solitaireappli.model;

public enum Suit {
    NONE("none"),
    HEART("heart"),
    DIAMOND("diamond"),
    CLUB("club"),
    SPADE("spade");


    private String libelle;

    private Suit(String libelle)
    {
        this.libelle=libelle;
    }


    @Override
    public String toString() {
        return  ""+libelle.charAt(0) ;
    }

}
