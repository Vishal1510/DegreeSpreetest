/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jvn.degreespree.widgets;

import com.jvn.degreespree.models.Player;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 *
 * @author john
 */
public class ScoreBoard extends VBox {
    
    private ArrayList<ScoreBoardRow> rows;
    private double scale = 1;
    Label deckInfo;
    Label playerName;
    Label playerLocation;
    
    public void init(double scale) {
        this.scale = scale;
        this.getStyleClass().add("score-board");
        
        this.setSpacing(8.0*scale);
        
        rows = new ArrayList<ScoreBoardRow>();
        
        Label nameCol = new Label("Name");
        nameCol.setPrefWidth(150*scale);
        
        Label learningCol = new Label("Learning");
        learningCol.setPrefWidth(100*scale);
        
        Label craftCol = new Label("Craft");
        craftCol.setPrefWidth(100*scale);
        
        Label integrityCol = new Label("Integrity");
        integrityCol.setPrefWidth(100*scale);
        
        Label qualityCol = new Label("Quality Points");
        qualityCol.setPrefWidth(100*scale);
        
        playerName = new Label("");
        deckInfo = new Label("Cards in deck: 35, Discards out of play: 0");
        playerLocation = new Label();
        
        
        HBox header = new HBox(nameCol, learningCol, craftCol, integrityCol, qualityCol);
        this.getChildren().addAll(header, deckInfo, playerLocation, playerName);

    }
    
    public void update() {
        for (ScoreBoardRow row : rows) {
            row.update();
        }
    }
    
    public void addPlayer(Player player) {
        if (player.isHuman()) {
            playerName.setText("Human player is " + player.getPlayerName());
            playerLocation.setText("You are " + player.getPlayerName() + " and you are in " + player.getBoardPosition().getPositionName());
        }
        ScoreBoardRow row = new ScoreBoardRow(player);
        rows.add(row);
        this.getChildren().add(this.getChildren().size() - 3, row);
    }
    
    private class ScoreBoardRow extends HBox {
        private Player player;
        private Label nameCol;
        private Label learningCol;
        private Label craftCol;
        private Label integrityCol;
        private Label qualityCol;
        
        public ScoreBoardRow(Player player) {
            super();
            this.player = player;
            
            nameCol = new Label(player.getPlayerName());
            nameCol.setPrefWidth(150*scale);
            
            learningCol = new Label(player.getLearning() + "");
            learningCol.setPrefWidth(100*scale);
            
            craftCol = new Label(player.getCraft() + "");
            craftCol.setPrefWidth(100*scale);
            
            
            integrityCol = new Label(player.getIntegrity() + "");
            integrityCol.setPrefWidth(100*scale);
            
            qualityCol = new Label(player.getQualityPoints() + "");
            qualityCol.setPrefWidth(100*scale);
            
            this.getChildren().addAll(nameCol, learningCol, craftCol, integrityCol, qualityCol);
            
        }
        
        public void update() {
            if (player.isHuman()) {
                playerLocation.setText("You are " + player.getPlayerName() + " and you are in " + player.getBoardPosition().getPositionName());
            }
            learningCol.setText(player.getLearning() + "");
            craftCol.setText(player.getCraft() + "");
            integrityCol.setText(player.getIntegrity() + "");
            qualityCol.setText(player.getQualityPoints() + "");
        }
    }
}
