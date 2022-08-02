package com.example.solitaireappli.model;

import java.util.List;

public interface CardCollection{



    public List<Card> getCards();

    public void setCards(List<Card> cards);
    public Card getCard(int i);


    public void setCard(int i, Card card);

    public Card getLastCard();
    public void addCard(Card card);

    public void removeLastCard();

    public void revealLastCard();
    public void hideLastCard();
    public void returnAll();
    public void revealAll();
    public boolean isAllVisible();

    void shuffle();

    void addCards(int number, CardCollection cardCollection);

    void addAllCards(CardCollection cardCollection);

    CardCollection getSubCardCollection(int number);

    int size();
}
