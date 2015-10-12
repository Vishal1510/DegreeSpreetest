package com.jvn.degreespree.models;

import com.jvn.degreespree.controllers.GameController;

import java.util.ArrayList;

/**
 * Created by john on 10/5/15.
 */
public class HumanPlayer implements Player {

    private String playerName;
    private BoardPosition boardPosition;

    private int learning = 0;
    private int craft = 0;
    private int integrity = 0;
    private int qualityPoints = 0;

    private ArrayList<Card> cards;
    private GameController gameController;

    private int movesLeft = 0;

    public HumanPlayer(String name) {
        cards = new ArrayList<Card>();
        playerName = name;
    }

    public void setBoardPosition(BoardPosition position) {

        boardPosition = position;
    }

    @Override
    public String getPlayerName(){
        return playerName;
    }

    @Override
    public BoardPosition getBoardPosition() {
        return boardPosition;
    }

    @Override
    public boolean isHuman() {
        return true;
    }

    @Override
    public void startTurn() {
        movesLeft = 3;
    }

    @Override
    public void endTurn() {

    }

    @Override
    public int getLearning() {
        return learning;
    }

    @Override
    public void setLearning(int learning) {
        this.learning = learning;
    }

    @Override
    public int getCraft() {
        return craft;
    }

    @Override
    public void setCraft(int craft) {
        this.craft = craft;
    }

    @Override
    public int getIntegrity() {
        return integrity;
    }

    @Override
    public void setIntegrity(int integrety) {
        this.integrity = integrety;
    }

    @Override
    public int getQualityPoints() {
        return qualityPoints;
    }

    @Override
    public void setQualityPoints(int qualityPoints) {
        this.qualityPoints = qualityPoints;
    }

    public int getMovesLeft() {
        return movesLeft;
    }

    public void setMovesLeft(int movesLeft) {
        this.movesLeft = movesLeft;
    }

    @Override
    public void decrementMovesLeft() {
        movesLeft--;
    }

    @Override
    public void bind(GameController controller) {
        gameController = controller;
    }
}
