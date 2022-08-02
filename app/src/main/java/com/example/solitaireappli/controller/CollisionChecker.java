package com.example.solitaireappli.controller;

import com.example.solitaireappli.view.ICollisionGame;
import com.example.solitaireappli.view.ICollisionGameDefausse;
import com.example.solitaireappli.view.ICollisionGamePiles;
import com.example.solitaireappli.view.ICollisionGamePioche;
import com.example.solitaireappli.view.ICollisionGameWithColumns;
import com.example.solitaireappli.view.ICollisionItem;

public class CollisionChecker {

    public CollisionChecker() {
    }

public String getInfoCollision(ICollisionGame collisionGame,int index)
{
    if(index == -1)
        return "?";
    else
        return collisionGame.findCollisionItem(collisionGame.getListCollisionItems().get(index));
}

    public int getCollisionItem(ICollisionGame collisionGame, float x, float y)
    {
        for(int i=0;i<collisionGame.getListCollisionItems().size();i++)
        {
            if(isCollisionItem(collisionGame.getListCollisionItems().get(i),collisionGame,x,y))
            {
                return i;
            }
        }
        return -1;
    }

    public boolean isCollisionItem(ICollisionItem item,ICollisionGame collisionGame, float x, float y)
    {
        float xMin = item.getXCollision() + collisionGame.getX0();
        float xMax = xMin + item.getWCollision();
        float yMin = item.getYCollision() + collisionGame.getY0();
        float yMax = yMin + item.getHCollision();
        return x >= xMin && x <= xMax && y >= yMin && y <= yMax;
    }

    public int collisionColumnChecker(ICollisionGameWithColumns collisionGameWithColumns, ICollisionItem collisionItem)
    {
        for(int i=0;i<collisionGameWithColumns.getListCollisionColumnsItems().size();i++)
        {
            if(isCollision(collisionItem,collisionGameWithColumns.getListCollisionColumnsItems().get(i)))
            {
                return i;
            }
        }
        return -1;
    }

    public boolean isCollision(ICollisionItem collisionItem1, ICollisionItem collisionItem2)
    {
        boolean tooTopCheck = collisionItem1.getYCollision() + collisionItem1.getHCollision() < collisionItem2.getYCollision();
        boolean tooRightCheck = collisionItem1.getXCollision() > collisionItem2.getWCollision() + collisionItem2.getXCollision();
        boolean tooBottomCheck = collisionItem1.getYCollision() > collisionItem2.getHCollision() + collisionItem2.getYCollision();
        boolean tooLeftCheck = collisionItem1.getXCollision() + collisionItem1.getWCollision() < collisionItem2.getXCollision();

        return !(tooLeftCheck || tooBottomCheck || tooTopCheck || tooRightCheck);
    }

    public boolean isPiocheCollision(ICollisionGamePioche collisionGamePioche, float x, float y )
    {
        return isCollisionItem(collisionGamePioche.getCollisionPiocheItem(),(ICollisionGame) collisionGamePioche, x,y);
    }

    public boolean isDefausseCollision(ICollisionGameDefausse collisionGameDefausse, float x, float y )
    {
        return isCollisionItem(collisionGameDefausse.getCollisionDefausseItem(),(ICollisionGame) collisionGameDefausse, x,y);
    }

    public int collisionPileChecker(ICollisionGamePiles collisionGamePiles, ICollisionItem collisionItem)
    {
        for(int i=0;i<collisionGamePiles.getListCollisionPilesItems().size();i++)
        {
            if(isCollision(collisionItem,collisionGamePiles.getListCollisionPilesItems().get(i)))
            {
                return i;
            }
        }
        return -1;
    }

}