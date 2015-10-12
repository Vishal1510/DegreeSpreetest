/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jvn.degreespree;

import com.jvn.degreespree.controllers.GameController;
import com.jvn.degreespree.utils.ScreenUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author john
 */
public class DegreeSpree extends Application {
    GameController controller;
    
    @Override
    public void start(Stage primaryStage) {
        
        Pane root = new StackPane();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/resources/css/stylesheet.css");
        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();
        ScreenUtils.init();
        controller = new GameController(primaryStage);
        controller.startGame();
    }   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
