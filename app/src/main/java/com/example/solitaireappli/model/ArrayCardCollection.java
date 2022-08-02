package com.example.solitaireappli.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayCardCollection implements CardCollection{

    private List<Card> cards;

    public ArrayCardCollection(List<Card> cards) {
        this.cards = cards;
    }

    public ArrayCardCollection() {
        this.cards = new ArrayList<Card>();
    }
    @Override
    public List<Card> getCards() {
        return cards;
    }

    @Override
    public void setCards(List<Card> cards) {

    }

    @Override
    public Card getCard(int i) {
        return cards.get(i);
    }

    @Override
    public void setCard(int i, Card card) {
cards.set(i,card);
    }

    @Override
    public Card getLastCard() {
        return cards.get(cards.size()-1);
    }

    @Override
    public void addCard(Card card) {
cards.add(card);
    }

    @Override
    public void removeLastCard() {
cards.remove(cards.size()-1);
    }

    @Override
    public void revealLastCard() {
        if(cards.size()>0)
            cards.get(cards.size()-1).setReturned(false);
    }

    @Override
    public void hideLastCard() {
        if(cards.size()>0)
            cards.get(cards.size()-1).setReturned(true);
    }

    @Override
    public void returnAll() {
        for(Card card : cards)
        {
            card.setReturned(true);
        }
    }

    @Override
    public void revealAll() {
        for(Card card : cards)
        {
            card.setReturned(false);
        }
    }

    @Override
    public boolean isAllVisible() {
        for(Card card : cards)
        {
            if(card.isReturned())
                return false;
        }
        return true;
    }

    @Override
    public void shuffle() {
        Collections.shuffle(cards);
    }

    @Override
    public void addCards(int number, CardCollection cardCollection) {
        for(int i=0;i<number;i++)
        {
            cards.add(cardCollection.getCard(cardCollection.getCards().size() - number + i ));

        }
        for(int i=0;i<number;i++) {
            cardCollection.removeLastCard();
        }


    }

    @Override
    public void addAllCards(CardCollection cardCollection) {
        this.addCards(cardCollection.size(),cardCollection);
    }

    @Override
    public CardCollection getSubCardCollection(int number) {
        CardCollection cardCollection = new ArrayCardCollection();
        int size = Math.min(cards.size(),number);
        for(int i=0;i<size;i++)
        {
            cardCollection.addCard(getCard(getCards().size() - size + i));

        }

        return cardCollection;
    }

    @Override
    public int size() {
        return cards.size();
    }
}
