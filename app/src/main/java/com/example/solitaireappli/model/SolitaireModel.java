package com.example.solitaireappli.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class SolitaireModel extends Observable implements IGameWithPiles {

    private List<CardCollection> piles;
    private CardCollection pioche;
    private CardCollection defausse;
    private List<CardCollection> deck;
    private CardCollection taken;
    public List<CardCollection> getPiles() {
        return piles;
    }

    public CardCollection getTaken() {
        return taken;
    }

    private SolitaireModelArchives solitaireModelArchives;

    public SolitaireModelArchives getSolitaireModelArchives() {
        return solitaireModelArchives;
    }

    public void setSolitaireModelArchives(SolitaireModelArchives solitaireModelArchives) {
        this.solitaireModelArchives = solitaireModelArchives;
    }

    private SolitaireMove solitaireMove;

    public SolitaireMove getSolitaireMove() {
        return solitaireMove;
    }

    public void setSolitaireMove(SolitaireMove solitaireMove) {
        this.solitaireMove = solitaireMove;
    }

    private SolitaireMoveChecker solitaireMoveChecker;

    public SolitaireMoveChecker getSolitaireMoveChecker() {
        return solitaireMoveChecker;
    }

    public void setSolitaireMoveChecker(SolitaireMoveChecker solitaireMoveChecker) {
        this.solitaireMoveChecker = solitaireMoveChecker;
    }

    public void setTaken(CardCollection taken) {
        this.taken = taken;
    }

    public void setPiles(List<CardCollection> piles) {
        this.piles = piles;
    }

    public CardCollection getPioche() {
        return pioche;
    }

    public void setPioche(CardCollection pioche) {
        this.pioche = pioche;
    }

    public CardCollection getDefausse() {
        return defausse;
    }

    public void setDefausse(CardCollection defausse) {
        this.defausse = defausse;
    }

    public List<CardCollection> getDeck() {
        return deck;
    }

    public void setDeck(List<CardCollection> deck) {
        this.deck = deck;
    }

    private int sourceIndex = -1;   // -1: null; -2: defausse

    public int getSourceIndex() {
        return sourceIndex;
    }

    public void setSourceIndex(int sourceIndex) {
        this.sourceIndex = sourceIndex;
    }

    private ISolitaireEndChecker solitaireEndChecker;

    public ISolitaireEndChecker getSolitaireEndChecker() {
        return solitaireEndChecker;
    }

    public void setSolitaireEndChecker(ISolitaireEndChecker solitaireEndChecker) {
        this.solitaireEndChecker = solitaireEndChecker;
    }

    private boolean isWin = false;

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }

    public SolitaireModel() {

    }

    public void loadGame(LoadParameters parameters)
    {
        CardCollection cards = new ArrayCardCollection();
        pioche = new ArrayCardCollection();
        defausse = new ArrayCardCollection();
        piles = new ArrayList<CardCollection>() ;
        deck = new ArrayList<CardCollection>() ;
        isWin = false;
        for(Rank rank: Rank.values() )
        {
            for(Suit suit: Suit.values() )
            {
if(rank != Rank.NONE && suit != Suit.NONE)
                {
                    cards.addCard(new Card(suit,rank,true));
                }
            }
        }
        cards.shuffle();
        CardCollection cardCollection;
        for(int i = 0;i<7;i++)
        {
            cardCollection = new ArrayCardCollection();
            cardCollection.addCards(i+1,cards);
            cardCollection.revealLastCard();
            deck.add(cardCollection);
        }
        for(int i = 0;i<4;i++)
        {
            cardCollection = new ArrayCardCollection();
            piles.add(cardCollection);
        }
        pioche.addCards(cards.getCards().size(),cards);

        solitaireMove = new SolitaireMove(this);
        solitaireMoveChecker = new SolitaireMoveChecker(this);
        solitaireModelArchives = new SolitaireModelArchives(this);
        solitaireEndChecker = new SolitaireEndChecker(this);
        taken = new ArrayCardCollection();
notifyObs();
    }

    public void notifyObs()
    {
        this.setChanged();
        this.notifyObservers();
    }


}
