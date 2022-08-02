package com.example.solitaireappli.model;

import java.util.ArrayList;
import java.util.List;

public class SolitaireModelArchives implements ICancelGame{

    private List<String> listMoves;

    private SolitaireModel solitaireModel;

    public SolitaireModelArchives() {
        listMoves = new ArrayList<String>();
    }

    public SolitaireModelArchives(SolitaireModel solitaireModel) {
        this.solitaireModel = solitaireModel;
        listMoves = new ArrayList<String>();
    }

    @Override
    public List<String> listMoves() {
        return listMoves;
    }

    @Override
    public void addMove(String newMove) {
        listMoves.add(newMove);
    }

    public void restaureLastMove()
    {
        if(listMoves.size() >0)
        {
            String moveString = listMoves.get(listMoves.size()-1);
            if(moveString == "pioche")
            {
                restaurePioche();
            }
            else
            {
                restaureMove(moveString);
            }

            listMoves.remove(listMoves.size()-1);
        }

    }

    private void restaureMove(String moveString) {
        String [] array = moveString.split("_");
        if(array[0].equals("deck"))
        {
            CardCollection cardCollectionSource;
            int sourceIndex = Integer.parseInt(array[1]);
            CardCollection cardCollectionTarget;
            int targetIndex = Integer.parseInt(array[2]);
            int numberCards = Integer.parseInt(array[3]);
            if(sourceIndex == -1)
            {
               cardCollectionSource = solitaireModel.getDefausse();
            }
            else {
                 cardCollectionSource = solitaireModel.getDeck().get(sourceIndex);
            }
            cardCollectionTarget = solitaireModel.getDeck().get(targetIndex);

            if(array[4].equals("1"))
            {
                cardCollectionSource.hideLastCard();
            }

            cardCollectionSource.addCards(numberCards,cardCollectionTarget);
        }
        if(array[0].equals("pile"))
        {
            CardCollection cardCollectionSource;
            int sourceIndex = Integer.parseInt(array[1]);
            CardCollection cardCollectionTarget;
            int targetIndex = Integer.parseInt(array[2]);
            int numberCards = 1;
            if(sourceIndex < 0 )
            {
                cardCollectionSource = solitaireModel.getDefausse();
            }
            else {
                cardCollectionSource = solitaireModel.getDeck().get(sourceIndex);
            }
            cardCollectionTarget = solitaireModel.getPiles().get(targetIndex);

            if(array[4].equals("1"))
            {
                cardCollectionSource.hideLastCard();
            }

            cardCollectionSource.addCards(numberCards,cardCollectionTarget);
        }


    }

    public void restaurePioche()
    {
        if(solitaireModel.getDefausse().size()>0)
        {
solitaireModel.getDefausse().hideLastCard();
solitaireModel.getPioche().addCards(1, solitaireModel.getDefausse());
        }
        else
        {
            solitaireModel.getDefausse().addCards(solitaireModel.getPioche().size(), solitaireModel.getPioche());
            solitaireModel.getDefausse().revealAll();
        }
    }


}
