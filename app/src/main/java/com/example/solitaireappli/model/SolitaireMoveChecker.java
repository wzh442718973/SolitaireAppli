package com.example.solitaireappli.model;

public class SolitaireMoveChecker {

    SolitaireModel solitaireModel;

    public SolitaireMoveChecker(SolitaireModel solitaireModel) {
        this.solitaireModel = solitaireModel;
    }

    public boolean checkTake(MoveSolitaireParametres parametres)
    {
        int source = parametres.getColumnSourceIndex();
        int numberTakenCards = parametres.getNumberCards();
        CardCollection cardsSource = solitaireModel.getDeck().get(source);

        int size = Math.min(cardsSource.size(),numberTakenCards);
        CardCollection subCards = cardsSource.getSubCardCollection(numberTakenCards);
        if(!subCards.isAllVisible())
        {
            return false;
        }
        return true;
    }

    public boolean checkPut(MoveSolitaireParametres parametres)
    {

        int target = parametres.getColumnTargetIndex();
        int numberPutCards = parametres.getNumberCards();

        CardCollection cardsTarget = solitaireModel.getDeck().get(target);

        int size = Math.min(cardsTarget.size(),numberPutCards);

        CardCollection cardsTaken = solitaireModel.getTaken().getSubCardCollection(numberPutCards);
        if(!cardsTaken.isAllVisible())
        {
            return false;
        }
        else
        {
            if(cardsTarget.size() == 0)
            {
                return cardsTaken.getCard(0).getRank() == Rank.KING;
            }
            else
            {
                CardComparator cardComparator = new CardComparator(cardsTarget.getLastCard(),cardsTaken.getCard(0));
                return cardComparator.isAllVisible() && cardComparator.isDifferentColor() && cardComparator.isJustHigher();
            }

        }

    }

    public boolean checkMoveToPile(MoveSolitaireParametres parametres)
    {
        int target = parametres.getPileTargetIndex();
        CardCollection cardsTarget = solitaireModel.getPiles().get(target);
        if(solitaireModel.getTaken().size() != 1)
        {
            return false;
        }
        else
        {
            if(cardsTarget.size() > 0)
            {
                CardComparator cardComparator = new CardComparator(solitaireModel.getTaken().getCard(0),cardsTarget.getLastCard());
                return cardComparator.isAllVisible() && cardComparator.isSameSuit() && cardComparator.isJustHigher();
            }
            else
            {
                return solitaireModel.getTaken().getCard(0).getRank() == Rank.AS;
            }
        }

    }


}
