/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jvn.degreespree.view;


import com.jvn.degreespree.controllers.GameController;
import com.jvn.degreespree.models.BoardPosition;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.utils.ScreenUtils;
import com.jvn.degreespree.widgets.PlayerIcon;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author john
 */
public class GameplayView implements View{
    
    private GameController gameController;
    
    private HashMap<Player, PlayerIcon> playerIcons = new HashMap<>(8);
    private ObservableList<BoardPosition> movablePositions;
    
    private StackPane root;
    private ScrollPane board;
    private ScrollPane controls;
    
    private Button move;
    private Button drawCard;
    private Button playCard;
    private Button nextCard;
    
    private ImageView cardImageView;
    
    private ListView<BoardPosition> moveToList;
    
    private ArrayList<VBox> positions;
    
    
    
    public GameplayView() {
        root = new StackPane();
        init();
    }
    
    private void init() {
        initBoard();
        initControls();
        
        VBox box = new VBox(board, controls);
        root.getChildren().add(box);
    }
    
    private void initBoard() {
        Pane boardPane = new Pane();
        
        int height = (int) (ScreenUtils.getHeight() * (2.0/3.0));
        int width = (int) ScreenUtils.getWidth();
        double scale = 1.0;
        
        Image boardImage = new Image("/resources/images/CSULBMap3.png");
        ImageView boardImageView = new ImageView(boardImage);
        
        if (boardImage.getWidth() < width) {
            boardImageView.setFitWidth(width);
            scale = (double) width/boardImage.getWidth();
        }
        
        boardImageView.setPreserveRatio(true);
        boardPane.getChildren().add(boardImageView);
        
        initPositions(scale);
        
        for (VBox box : positions) {
            boardPane.getChildren().add(box);
        }
        
        board = new ScrollPane();
        board.setPrefWidth(width);
        board.setPrefHeight(height);
        board.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        board.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        board.setPannable(true);
        board.setContent(boardPane);
        
    }
    
    private void initPositions(double scale) {
        positions = new ArrayList<>(21);
        
        positions.add(createBoardSquare(20,100,0,0,400,620, scale));  //George Allen Field
        positions.add(createBoardSquare(20,25,400,0,500,240, scale));  //Japanese Garden
        positions.add(createBoardSquare(20,50,900,0,700,480, scale));  //Student Parking
        positions.add(createBoardSquare(20,25,400,250,500,240, scale));  //Pyramid
        positions.add(createBoardSquare(20,50,0,620,300,850, scale));  //West Walkway
        positions.add(createBoardSquare(20,50,400,500,580,350, scale));  //Rec Center
        positions.add(createBoardSquare(20,50,1000,500,660,350, scale));  //Forbidden parking
        positions.add(createBoardSquare(20,50,0,1600,450,400, scale));  //Library
        positions.add(createBoardSquare(20,50,450,1600,550,400, scale));  //LA 5
        positions.add(createBoardSquare(20,50,1000,1600,650,400, scale));  //Bratwurst Hall
        positions.add(createBoardSquare(20,10,1440,930,170,650, scale));  //East Walkway
        positions.add(createBoardSquare(20,50,150,860,400,270, scale));  //Computer Lab
        positions.add(createBoardSquare(20,10,150,1130,650,170, scale));  //North Hall
        positions.add(createBoardSquare(20,50,150,1320,400,270, scale));  //Room of Retirement
        positions.add(createBoardSquare(20,50,580,860,400,270, scale));  //ECS 302
        positions.add(createBoardSquare(20,10,800,1130,650,170, scale));  //South Hall
        positions.add(createBoardSquare(20,50,580,1320,220,270, scale));  //Elevators
        positions.add(createBoardSquare(20,50,800,1320,400,270, scale));  //ECS 308
        positions.add(createBoardSquare(20,50,1020,860,220,270, scale));  //Eat Club
        positions.add(createBoardSquare(20,50,1230,860,220,270, scale));  //Conference Room
        positions.add(createBoardSquare(20,50,1200,1320,220,270, scale));  //Lactation Lounge
        
        
    }
    
    private VBox createBoardSquare(int paddingLeft, int paddingTop, int x, int y, int width, int height, double scale) {
        VBox box = new VBox();
        box.setLayoutX(scale * x);
        box.setLayoutY(scale * y);
        box.setPrefSize(scale * width, scale * height);
        
        box.setPadding(new Insets(paddingTop*scale, 0, 0, paddingLeft*scale));
        box.setSpacing(10*scale);

        return box;
    }

    private void initControls() {
        double scale = 1.0;
        controls = new ScrollPane();
        
        VBox moveDisplay = createMoveDisplay(scale);
        VBox cardDisplay = createCardDisplay(scale);
        

        initScoreBoard(scale);
        
        HBox controlBox = new HBox(moveDisplay, cardDisplay);

        controls.setContent(controlBox);
        
    }
    
    private VBox createMoveDisplay(double scale) {
        initMoveButton(scale);
        initDrawCardButton(scale);
        initMoveList(scale);

        HBox buttons = new HBox(move, drawCard);
        
        VBox moveDisplay = new VBox(buttons, moveToList);
        
        return moveDisplay;
    }
    
    private void initMoveButton(double scale) {
        move = new Button("Move");
        
        move.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BoardPosition selected = moveToList.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    gameController.movePlayer(selected);
                }
            }
        });
    }
    
    private void initDrawCardButton(double scale) {
        drawCard = new Button("Draw Card");
    }
    
    private void initMoveList(double scale) {
        moveToList = new ListView<>();
        movablePositions = FXCollections.observableArrayList(new ArrayList<BoardPosition>());
        moveToList.setItems(movablePositions);
        moveToList.setEditable(true);
    }
    
    private VBox createCardDisplay(double scale) {
        initPlayCardButton(scale);
        initNextCardButton(scale);
        initCardImage(scale);
        
        HBox buttons = new HBox(nextCard, playCard);
        VBox cardDisplay = new VBox(buttons, cardImageView);
        
        return cardDisplay;
    }
    
    private void initPlayCardButton(double scale) {
        playCard = new Button("Play Card");
        
        playCard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                gameController.playCard(null);

            }
        });
    }
    
    private void initNextCardButton(double scale) {
        nextCard = new Button("Next Card");
    }
    
    private void initCardImage(double scale) {
        Image cardImage = new Image("/resources/images/cards/meet_the_dean.png");
        cardImageView = new ImageView(cardImage);
    }
    
    private void initScoreBoard(double scale) {
        
    }
    
    @Override
    public StackPane getView() {
        return root;
    }

    
    // Public methods for board
    public void addPlayers(ArrayList<Player> players) {
        for (Player player : players) {
            addPlayer(player);
        }
    }

    public void addPlayer(Player player) {
        PlayerIcon icon = new PlayerIcon();
        icon.setup(player);
        playerIcons.put(player, icon);

        movePlayer(player);
    }
    
    public void movePlayer(Player player) {
        PlayerIcon icon = playerIcons.get(player);

        if (icon.getParent() != null) {
            VBox parent = (VBox) icon.getParent();
            parent.getChildren().remove(icon);
        }

        positions.get(player.getBoardPosition().getIndex()).getChildren().add(icon);
    }
    
    // Public methods for Control Panel
    
    public void updateMovableLocation(ArrayList<BoardPosition> positions) {
        movablePositions.clear();
        movablePositions.addAll(positions);
        moveToList.setItems(movablePositions);
    }
    
    public void bind(GameController controller) {
        this.gameController = controller;
    }
}
