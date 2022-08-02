package com.example.solitaireappli.model;

public class MoveSolitaireParametres {

   private int numberCards;

   private boolean isToTake;

   private boolean isDefausseSource;

   private int columnSourceIndex;
    private int columnTargetIndex;

    private int pileTargetIndex;

    public int getNumberCards() {
        return numberCards;
    }

    public void setNumberCards(int numberCards) {
        this.numberCards = numberCards;
    }

    public boolean isToTake() {
        return isToTake;
    }

    public void setToTake(boolean toTake) {
        isToTake = toTake;
    }

    public boolean isDefausseSource() {
        return isDefausseSource;
    }

    public void setDefausseSource(boolean defausseSource) {
        isDefausseSource = defausseSource;
    }

    public int getColumnSourceIndex() {
        return columnSourceIndex;
    }

    public void setColumnSourceIndex(int columnSourceIndex) {
        this.columnSourceIndex = columnSourceIndex;
    }

    public int getColumnTargetIndex() {
        return columnTargetIndex;
    }

    public void setColumnTargetIndex(int columnTargetIndex) {
        this.columnTargetIndex = columnTargetIndex;
    }

    public int getPileTargetIndex() {
        return pileTargetIndex;
    }

    public void setPileTargetIndex(int pileTargetIndex) {
        this.pileTargetIndex = pileTargetIndex;
    }

    public MoveSolitaireParametres() {
    }
}
