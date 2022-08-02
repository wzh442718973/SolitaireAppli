package com.example.solitaireappli.view;

import java.util.ArrayList;
import java.util.List;

public class ArrayCardCollectionView implements CardCollectionView{


    private List<CardView> cardViews;

    public ArrayCardCollectionView(List<CardView> cardViews) {
        this.cardViews = cardViews;
    }
    public ArrayCardCollectionView() {
        this.cardViews = new ArrayList<CardView>();
    }


    @Override
    public List<CardView> getCardViews() {
        return cardViews;
    }

    @Override
    public CardView get(int i) {
        return cardViews.get(i);
    }

    @Override
    public CardView getLastCardView() {
       return this.cardViews.get(this.cardViews.size()-1);
    }

    @Override
    public void addCardView(CardView cardView) {
cardViews.add(cardView);
    }

    @Override
    public int size() {
        return cardViews.size();
    }

    @Override
    public void clear() {
        cardViews.clear();
    }
}
