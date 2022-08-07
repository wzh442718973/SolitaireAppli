package com.example.solitaireappli.controller;


import com.example.solitaireappli.view.ICollisionGame;
import com.example.solitaireappli.view.SolitaireView;

public class DownTouchSolitaire implements SolitaireTouchEvent{

    public DownTouchSolitaire() {
    }

    @Override
    public boolean TouchSolitaire(SolitaireView solitaireView, SolitaireController solitaireController, float x, float y) {
        CollisionChecker collisionChecker = new CollisionChecker();
    int selectedIndexCollision =  collisionChecker.getCollisionItem(solitaireView, x,y);
    if(selectedIndexCollision != -1) {
    String strSelected = collisionChecker.getInfoCollision(solitaireView,selectedIndexCollision);
    solitaireView.setDevText("down" + x + "_" + y + " collision " + strSelected);
    int column = Integer.parseInt((strSelected.split("_"))[0]);
    int row = Integer.parseInt((strSelected.split("_"))[1]);
    if(solitaireController.takeCardsFromColumn(column, row))
    {
        solitaireView.xEvent = x;
        solitaireView.yEvent = y;

        solitaireView.xTaken = solitaireView.getSetParameters().getxDeck() + column * solitaireView.getSetParameters().getdXDeck();
        solitaireView.yTaken = solitaireView.getSetParameters().getyDeck() + row * solitaireView.getSetParameters().getdYDeck();
        for(int i=0;i< solitaireView.getTakenView().size();i++)
        {
            solitaireView.getTakenView().get(i).setX(solitaireView.xTaken);
            solitaireView.getTakenView().get(i).setY(solitaireView.yTaken + i*solitaireView.getSetParameters().getdYDeck());
        }
        solitaireView.setToForeGround(solitaireView.getTakenView());
    }


}
else
    {
        if(collisionChecker.isPiocheCollision(solitaireView,x,y))
        {
solitaireController.piocher();
        }
        if(collisionChecker.isDefausseCollision(solitaireView,x,y))
        {
            if(solitaireController.takeCardFromDefausse())
            {
            solitaireView.xEvent = x;
            solitaireView.yEvent = y;
            solitaireView.xTaken = solitaireView.getSetParameters().getxDefausse();
            solitaireView.yTaken = solitaireView.getSetParameters().getyDefausse();

            solitaireView.getTakenView().get(0).setX(solitaireView.xTaken);
            solitaireView.getTakenView().get(0).setY(solitaireView.yTaken);
            }
        }
    }

    solitaireView.setDevText("down"  + x + "_" + y + " collision -1" );
        return false;
    }

}
