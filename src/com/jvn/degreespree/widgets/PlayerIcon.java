/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jvn.degreespree.widgets;

import com.jvn.degreespree.models.Player;
import javafx.scene.control.Label;


/**
 *
 * @author john
 */
public class PlayerIcon extends Label {
    
    public void setup(Player player) {
        this.setText(player.getPlayerName());
        this.getStyleClass().add("player-icon");
    }
    
}
