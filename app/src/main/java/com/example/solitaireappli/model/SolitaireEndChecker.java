package com.example.solitaireappli.model;

public class SolitaireEndChecker implements ISolitaireEndChecker{

    private SolitaireModel solitaireModel;

    public SolitaireEndChecker(SolitaireModel solitaireModel)
    {
      this.solitaireModel = solitaireModel;
    }

    @Override
    public boolean checkEnd() {
        if (solitaireModel.getPiles().size() == 4)
        {
            for(CardCollection cardCollection: solitaireModel.getPiles())
            {
                if(cardCollection.size() < 13)
                    return false;
            }
            return true;
        }
        return false;

    }
}
