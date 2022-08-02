package com.example.solitaireappli.model;

public class Card implements ICard{
    private Suit suit;
    private Rank rank;

    public boolean isReturned() {
        return isReturned;
    }



    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    boolean isReturned;

    public Card(Suit suit, Rank rank, boolean isReturned) {
        this.suit = suit;
        this.rank = rank;
        this.isReturned = isReturned;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public String toString()
    {
        return !isReturned ? rank + "" + suit : "?";
    }

}
