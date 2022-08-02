package com.example.solitaireappli.view;

import com.example.solitaireappli.model.Card;

import java.util.List;

public interface CardCollectionView {

    public List<CardView> getCardViews();
    public CardView get(int i);
    public CardView getLastCardView();
    public void addCardView(CardView cardView);

    int size();

    void clear();
}
