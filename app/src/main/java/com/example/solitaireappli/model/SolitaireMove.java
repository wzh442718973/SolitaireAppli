package com.example.solitaireappli.model;

public class SolitaireMove {

    private SolitaireModel solitaireModel;

    public SolitaireMove(SolitaireModel solitaireModel) {
        this.solitaireModel = solitaireModel;
    }

    public SolitaireMove() {


    }

    public void performTake(MoveSolitaireParametres parametres)
    {
int source = parametres.getColumnSourceIndex();
int target = parametres.getColumnTargetIndex();
int numberTakenCards = parametres.getNumberCards();
CardCollection cardsSource = solitaireModel.getDeck().get(source);

int size = Math.min(cardsSource.size(),numberTakenCards);
//solitaireModel.getTaken().addCards(numberTakenCards, cardsSource.getSubCardCollection(numberTakenCards));
solitaireModel.getTaken().addCards(numberTakenCards, cardsSource);


    }

    public void performPut(MoveSolitaireParametres parametres)
    {
        String moveString = "";
        boolean reveal = false;
        int source = parametres.getColumnSourceIndex();
        int target = parametres.getColumnTargetIndex();
        int numberPutCards = parametres.getNumberCards();

        CardCollection cardsTarget = solitaireModel.getDeck().get(target);

        int size = Math.min(cardsTarget.size(),numberPutCards);
        cardsTarget.addCards(numberPutCards,solitaireModel.getTaken());
        if(source >=0)
        {

            CardCollection cardsSource = solitaireModel.getDeck().get(source);
            if(cardsSource.size()>0)
            {
                if(cardsSource.getLastCard().isReturned() == true)
                {
                    cardsSource.revealLastCard();
                    reveal = true;
                }
                else
                {
                    reveal = false;
                }
            }
            else
            {
                reveal = false;
            }

        }

        moveString = "deck_" + source + "_" + target + "_" + numberPutCards + "_" +(reveal ? "1" : "0");

        solitaireModel.getSolitaireModelArchives().addMove(moveString);


    }

    public void newPioche(MoveSolitaireParametres parametres)
    {
        solitaireModel.getPioche().addCards(solitaireModel.getDefausse().size(), solitaireModel.getDefausse());
        solitaireModel.getPioche().returnAll();
        String moveString = "pioche";
        solitaireModel.getSolitaireModelArchives().addMove(moveString);

    }

    public void piocher(MoveSolitaireParametres parametres)
    {
        solitaireModel.getDefausse().addCards(1, solitaireModel.getPioche());
        solitaireModel.getDefausse().revealLastCard();
        String moveString = "pioche";
        solitaireModel.getSolitaireModelArchives().addMove(moveString);


    }

    public void takeToDefausse(MoveSolitaireParametres parametres)
    {
        solitaireModel.getTaken().addCards(1, solitaireModel.getDefausse());
    }


    public void moveToPile(MoveSolitaireParametres parametres)
    {
        String moveString = "";
        boolean reveal = false;
        int target = parametres.getPileTargetIndex();
        CardCollection cardsTarget = solitaireModel.getPiles().get(target);
        cardsTarget.addCards(1,solitaireModel.getTaken());

        int source = parametres.getColumnSourceIndex();
        if(source >=0)
        {

            CardCollection cardsSource = solitaireModel.getDeck().get(source);
            if(cardsSource.size()>0)
            {
                if(cardsSource.getLastCard().isReturned() == true)
                {
                    cardsSource.revealLastCard();
                    reveal = true;
                }
                else
                {
                    reveal = false;
                }
            }
            else
            {
                reveal = false;
            }

        }

        moveString = "pile_" + source + "_" + target + "_1_"+ (reveal ? "1" : "0");
solitaireModel.getSolitaireModelArchives().addMove(moveString);

    }

    public void cancelMove(MoveSolitaireParametres parametres)
    {
        int sourceIndex = solitaireModel.getSourceIndex();
        CardCollection cardsSource = solitaireModel.getTaken();
        CardCollection cardsTarget;
        if(sourceIndex != -1)
        {
            if(sourceIndex == -2)
            {
                cardsTarget = solitaireModel.getDefausse();
            }
            else
            {
                cardsTarget = solitaireModel.getDeck().get(sourceIndex);
            }
            cardsTarget.addAllCards(cardsSource);
        }
    }

}
