package com.example.solitaireappli.model;

import java.util.ArrayList;
import java.util.List;

public class CardComparator {

    private Card card1;
    private Card card2;

    public void setCard1(Card card1) {
        this.card1 = card1;
    }

    public void setCard2(Card card2) {
        this.card2 = card2;
    }

    private List<Suit> redSuits = new ArrayList<Suit>();
    private List<Suit> blackSuits = new ArrayList<Suit>();

    public CardComparator(Card card1, Card card2) {
        this.card1 = card1;
        this.card2 = card2;

        initLists();
    }

    private void initLists()
    {
        redSuits.add(Suit.HEART);
        redSuits.add(Suit.DIAMOND);
        blackSuits.add(Suit.SPADE);
        blackSuits.add(Suit.CLUB);
    }

    private boolean isRed(Card card)
    {
        return redSuits.contains(card.getSuit());
    }

    private boolean isBlack(Card card)
    {
        return blackSuits.contains(card.getSuit());
    }

    public boolean isJustHigher()
    {
        return this.card1.getRank().getValue() ==((card2.getRank()).getValue()+1);
    }

    public boolean isDifferentColor()
    {
        return (isRed(this.card1) && isBlack(this.card2)) || (isRed(this.card2) && isBlack(this.card1));
    }

    public boolean isSameSuit()
    {
        return this.card1.getSuit() == this.card2.getSuit();
    }

    public boolean isAllVisible()
    {
        return !(this.card1 .isReturned() || this.card2.isReturned());
    }
}
