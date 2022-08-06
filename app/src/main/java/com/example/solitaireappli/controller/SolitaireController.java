package com.example.solitaireappli.controller;

import com.example.solitaireappli.model.CardCollection;
import com.example.solitaireappli.model.LoadParameters;
import com.example.solitaireappli.model.MoveSolitaireParametres;
import com.example.solitaireappli.model.SolitaireModel;
import com.example.solitaireappli.view.SolitaireView;

public class SolitaireController {

    private SolitaireView solitaireView;
    private SolitaireModel solitaireModel;

    public SolitaireController(SolitaireView solitaireView, SolitaireModel solitaireModel) {
        this.solitaireView = solitaireView;
        this.solitaireModel = solitaireModel;
    }

    public SolitaireController(SolitaireView solitaireView) {
        this.solitaireView = solitaireView;
    }

    public boolean takeCardsFromColumn(int columnIndex, int beginingCardIndex) {
        CardCollection cardsToTake = solitaireModel.getDeck().get(columnIndex);
        int siz = cardsToTake.getCards().size();
        int numberToTake = siz-beginingCardIndex;

        MoveSolitaireParametres parametres = new MoveSolitaireParametres();
        parametres.setColumnSourceIndex(columnIndex);
        parametres.setNumberCards(numberToTake);
        if(solitaireModel.getSolitaireMoveChecker().checkTake(parametres))
        {
            solitaireModel.getSolitaireMove().performTake(parametres);
            solitaireModel.setSourceIndex(columnIndex);
            solitaireModel.notifyObs();
            return true;
        }
        else
        {
            return false;
        }
    }



    public boolean putIntoColumn(SolitaireView solitaireView, int targetColumnIndex) {
        if(targetColumnIndex != -1 && targetColumnIndex != solitaireModel.getSourceIndex() && solitaireModel.getSourceIndex() != -1)
        {
//            try
//            {

            MoveSolitaireParametres parametres = new MoveSolitaireParametres();
            parametres.setNumberCards(solitaireModel.getTaken().size());
            parametres.setColumnSourceIndex(solitaireModel.getSourceIndex());
            parametres.setColumnTargetIndex(targetColumnIndex);

            if( solitaireModel.getSolitaireMoveChecker().checkPut(parametres))
                {
                    solitaireModel.getSolitaireMove().performPut(parametres);
                    // solitaireView.getScoreManager().scoreUpdate("move");

//            }
//            catch(IllegalMoveException exception)
//            {
//                rollbackTake(spiderView);
//                return false;
//            }
                    solitaireModel.notifyObs();
                }
            else
            {
                return false;
            }

        }
        else
        {
            return false;
        }
        solitaireModel.setSourceIndex(-1);

        return true;
    }

    public void rollbackTake(SolitaireView solitaireView) {

        MoveSolitaireParametres parametres = new MoveSolitaireParametres();
        solitaireModel.getSolitaireMove().cancelMove(parametres);
        solitaireModel.notifyObs();
    }

    public void piocher() {
        MoveSolitaireParametres parametres = new MoveSolitaireParametres();
        if(solitaireModel.getPioche().size()>0)
            solitaireModel.getSolitaireMove().piocher(parametres);
        else
            solitaireModel.getSolitaireMove().newPioche(parametres);
        solitaireModel.notifyObs();
    }

    public boolean takeCardFromDefausse() {
        MoveSolitaireParametres parametres = new MoveSolitaireParametres();
        if(solitaireModel.getDefausse().size()>0)
        {
            solitaireModel.getSolitaireMove().takeToDefausse(parametres);
            solitaireModel.setSourceIndex(-2);
        }
          else
        {
            return false;
        }
        solitaireModel.notifyObs();
        return true;
    }

    public boolean putIntoPile(SolitaireView solitaireView, int selectedPile) {
        MoveSolitaireParametres parametres = new MoveSolitaireParametres();
        if(selectedPile != -1 && solitaireModel.getTaken().size() == 1)
        {
            parametres.setColumnSourceIndex(solitaireModel.getSourceIndex());
            parametres.setPileTargetIndex(selectedPile);
            if(solitaireModel.getSolitaireMoveChecker().checkMoveToPile(parametres)) {
                solitaireModel.getSolitaireMove().moveToPile(parametres);
                if(solitaireModel.getSolitaireEndChecker().checkEnd())
                {
                    solitaireModel.setWin(true);
                }
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
        solitaireModel.notifyObs();
        solitaireModel.setSourceIndex(-1);
        return true;
    }

    public void nouveauJeu() {
        LoadParameters parameters = new LoadParameters();
        solitaireModel.loadGame(parameters);
        solitaireModel.notifyObs();
    }

    public void lastMove()
    {
        if(!solitaireModel.isWin()) {
            solitaireModel.getSolitaireModelArchives().restaureLastMove();
            solitaireModel.notifyObs();
        }
    }
}
