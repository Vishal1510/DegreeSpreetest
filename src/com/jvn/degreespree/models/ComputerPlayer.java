package com.jvn.degreespree.models;

import com.jvn.degreespree.controllers.GameController;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by john on 10/10/15.
 */
public class ComputerPlayer implements Player {
    private static final String TAG = "ComputerPlayer";

    private String playerName;
    private BoardPosition boardPosition;
    private int learning = 0;
    private int craft = 0;
    private int integrity = 0;
    private int qualityPoints = 0;
    private Random r;

    private GameController gameController;

    private int movesLeft = 0;

    private ArrayList<Card> cards;

    public ComputerPlayer(String name) {
        cards = new ArrayList<>();
        playerName = name;
        r = new Random();
    }

    @Override
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
        return false;
    }

    @Override
    public void startTurn() {
 
        movesLeft = 3;

        while(movesLeft > 0) {
            boardPosition = getRandomPosition();
            gameController.movePlayer(boardPosition);
            
        }
        playRandomCard();
    }

    private BoardPosition getRandomPosition() {
        
        int index = boardPosition.getNearbyPositions().get(r.nextInt(boardPosition.getNearbyPositions().size()));
        return gameController.getGameBoard().getPosition(index);
    }

    private void playRandomCard() {
        gameController.playCard(null);
    }

    @Override
    public void endTurn() {

    }

    public int getLearning() {
        return learning;
    }

    public void setLearning(int learning) {
        this.learning = learning;
    }

    public int getCraft() {
        return craft;
    }

    public void setCraft(int craft) {
        this.craft = craft;
    }

    public int getIntegrity() {
        return integrity;
    }

    public void setIntegrity(int integrety) {
        this.integrity = integrety;
    }

    public int getQualityPoints() {
        return qualityPoints;
    }

    public void setQualityPoints(int qualityPoints) {
        this.qualityPoints = qualityPoints;
    }

    @Override
    public int getMovesLeft() {
        return movesLeft;
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
