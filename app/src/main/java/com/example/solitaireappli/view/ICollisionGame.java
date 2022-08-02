package com.example.solitaireappli.view;

import java.util.HashMap;
import java.util.List;

public interface ICollisionGame {

    public List<ICollisionItem> getListCollisionItems();
    public String findCollisionItem(ICollisionItem iCollisionItem);
    public float getX0();
    public float getY0();

}
