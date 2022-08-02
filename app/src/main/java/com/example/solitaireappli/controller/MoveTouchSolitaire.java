package com.example.solitaireappli.controller;

import com.example.solitaireappli.view.SolitaireView;

public class MoveTouchSolitaire implements SolitaireTouchEvent{

    public MoveTouchSolitaire() {
    }

    @Override
    public boolean TouchSolitaire(SolitaireView solitaireView, SolitaireController solitaireController, float x, float y) {
      // solitaireView.setDevText("move " + x + "_" + y);

        float dx,dy;

        if(solitaireView.getTakenView().size()>0)
        {
            dx = x-solitaireView.xEvent;
            dy = y-solitaireView.yEvent;
            for(int i=0;i< solitaireView.getTakenView().size();i++)
            {
                solitaireView.getTakenView().get(i).setX(solitaireView.getTakenView().get(i).getX() + dx);
                solitaireView.getTakenView().get(i).setY(solitaireView.getTakenView().get(i).getY() + dy);
            }


            solitaireView.xEvent = x;
            solitaireView.yEvent = y;

        }
        return false;
    }
}
