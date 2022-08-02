package com.example.solitaireappli.model;

import java.util.List;

public interface ICancelGame {

    public List<String> listMoves();

    public void addMove(String newMove);
}
