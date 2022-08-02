package com.example.solitaireappli.controller;

import com.example.solitaireappli.view.SolitaireView;

public class UpTouchSolitaire implements SolitaireTouchEvent{

    public UpTouchSolitaire() {
    }

    @Override
    public boolean TouchSolitaire(SolitaireView solitaireView, SolitaireController solitaireController, float x, float y) {
      //  solitaireView.setDevText("up " + x + "_" + y);
        CollisionChecker collisionChecker = new CollisionChecker();
        if(solitaireView.getTakenView().size()>0)
            {
                int selectedColumn = collisionChecker.collisionColumnChecker(solitaireView,solitaireView.getTakenView().get(0));

                if(selectedColumn != -1)
                {
                    if(!solitaireController.putIntoColumn(solitaireView, selectedColumn))
                    {
                        solitaireController.rollbackTake(solitaireView);
                    }
                  //  spiderController.checkDone(spiderView);
                }
                else
                {
                    int selectedPile = collisionChecker.collisionPileChecker(solitaireView,solitaireView.getTakenView().get(0));
                    if(selectedPile != -1)
                    {
                        if(!solitaireController.putIntoPile(solitaireView, selectedPile))
                        {
                            solitaireController.rollbackTake(solitaireView);
                        }
                    }
                    else
                    {
                        solitaireController.rollbackTake(solitaireView);
                    }

                }

            }




        return true;
    }
}
