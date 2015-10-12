/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jvn.degreespree.controllers;

import com.jvn.degreespree.models.BoardPosition;
import com.jvn.degreespree.models.Card;
import com.jvn.degreespree.models.GameBoard;
import com.jvn.degreespree.models.GameSettings;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.view.GameplayView;
import java.util.ArrayList;
import javafx.stage.Stage;

/**
 *
 * @author john
 */
public class GameController {
    Stage stage;
    GameplayView gameplayView;

    private GameSettings gameSettings;
    private ArrayList<Player> players;
    private Player currentPlayersTurn;
    private int currentPlayerIndex = 0;
    private GameBoard gameBoard;

    
    
    public GameController(Stage stage) {
        this.stage = stage;
        
        gameplayView = new GameplayView();
        gameplayView.bind(this);
        
        init();
    }
    
    private void init() {
        gameBoard = new GameBoard();
    }
    
    public void startGame() {
        gameSettings = new GameSettings();
        players = gameSettings.getPlayers();

        for (Player player : players) {
            player.setBoardPosition(gameBoard.getPosition(17));
            player.bind(this);
        }

        showGamePlayView();
        
        addPlayers(players);

        startTurn(players.get(currentPlayerIndex));
    }
    
    private void startTurn(Player player) {
        currentPlayersTurn = player;
        if (player.isHuman()) {
            player.startTurn();
            ArrayList<BoardPosition> positions = gameBoard.getPositions(player.getBoardPosition().getNearbyPositions());
            gameplayView.updateMovableLocation(positions);
        } else {
            // Eventually disable components so player cant screw the game up
            player.startTurn();
        }

    }
    
    private void nextTurn() {
        if (!gameHasEnded()) {
            currentPlayerIndex = (currentPlayerIndex + 1) % 3;
            Player nextPlayer = players.get(currentPlayerIndex);
            startTurn(nextPlayer);
        } else {
            endGame();
        }

    }
    
    public void movePlayer(BoardPosition position) {
        if (currentPlayersTurn.getMovesLeft() > 0) {
            currentPlayersTurn.setBoardPosition(position);
            gameplayView.movePlayer(currentPlayersTurn);
            currentPlayersTurn.decrementMovesLeft();

            if (currentPlayersTurn.isHuman()) {
                // must change
                ArrayList<BoardPosition> movableLocations = gameBoard.getPositions(position.getNearbyPositions());
                gameplayView.updateMovableLocation(movableLocations);
            }

        }
    }
    
    public void playCard(Card card) {
        // At somepoint play the card yo

        endTurn(currentPlayersTurn);
        nextTurn();
    }
    
    private void endTurn(Player player) {
        player.endTurn();
    }
    
    private void addPlayers(ArrayList<Player> players) {
        gameplayView.addPlayers(players);
    }
    
    public void showGamePlayView() {
        stage.getScene().setRoot(gameplayView.getView());
    }
    
    private boolean gameHasEnded() {
        // check for win conditions
        return false;
    }
    
    private void endGame() {

    }
    
    public GameBoard getGameBoard() {
        return gameBoard;
    }
    
}
