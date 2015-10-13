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
import com.jvn.degreespree.widgets.ScoreBoard;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

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
    private Pane controls;
    
    private Button move;
    private Button drawCard;
    private Button playCard;
    private Button nextCard;
    
    private ScoreBoard scoreBoard;
    
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
        
        VBox box = new VBox(5);
        box.getChildren().addAll(board, controls);
        root.getChildren().add(box);
    }
    
    private void initBoard() {
        Pane boardPane = new Pane();
        
        
        double height =  (ScreenUtils.getHeight() * (2.0/3.0));
        double width =  ScreenUtils.getWidth();
        double scale = 1.0;
        
        Image boardImage = new Image("/resources/images/CSULBMap3.png");
        ImageView boardImageView = new ImageView(boardImage);
        
        if (boardImage.getWidth() < width) {
            boardImageView.setFitWidth(width-2);
            scale = (double) (width-2)/boardImage.getWidth();
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
        double x = ScreenUtils.getWidth();
        double y = ScreenUtils.getHeight()/3.0 - 5;
        controls = new Pane();
        
        controls.setPrefSize(x, y);
        
        VBox moveDisplay = createMoveDisplay(x,y);
        moveDisplay.setPrefSize(x/4.0, y/3.0);
        
        VBox cardDisplay = createCardDisplay(x,y);
        
        VBox scoreDisplay = createScoreDisplay(x,y);
        scoreDisplay.setPrefSize(x/3.0, y/3.0);
        
        HBox controlBox = new HBox(x/64.0);
        controlBox.getChildren().addAll(moveDisplay, cardDisplay, scoreDisplay);
        

        controls.getChildren().add(controlBox);
        
    }
    
    private VBox createMoveDisplay(double x, double y) {
        
        initMoveButton(50, 20);
        initDrawCardButton(50, 20);
        initMoveList(x, y-22);

        HBox buttons = new HBox(2);
        buttons.getChildren().addAll(move, drawCard);
        
        VBox moveDisplay = new VBox(2);
        moveDisplay.getChildren().addAll(buttons, moveToList);
        
        return moveDisplay;
    }
    
    private void initMoveButton(double width, double height) {
        move = new Button("Move");
        move.setPrefHeight(height);
        
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
    
    private void initDrawCardButton(double width, double height) {
        drawCard = new Button("Draw Card");
        drawCard.setPrefHeight(height);
    }
    
    private void initMoveList(double width, double height) {
        moveToList = new ListView<>();
        movablePositions = FXCollections.observableArrayList(new ArrayList<BoardPosition>());
        moveToList.setItems(movablePositions);
        moveToList.setEditable(false);
        
    }
    
    private VBox createCardDisplay(double x, double y) {
        initPlayCardButton(50, 20);
        initNextCardButton(50, 20);
        initCardImage(x, y-32);
        
        HBox buttons = new HBox(2);
        buttons.getChildren().addAll(nextCard, playCard);
        VBox cardDisplay = new VBox(2);
        cardDisplay.getChildren().addAll(buttons, cardImageView);
        
        return cardDisplay;
    }
    
    private void initPlayCardButton(double width, double height) {
        playCard = new Button("Play Card");
        playCard.setPrefHeight(height);
        
        playCard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                gameController.playCard(null);

            }
        });
    }
    
    private void initNextCardButton(double width, double height) {
        nextCard = new Button("Next Card");
        nextCard.setPrefHeight(height);
    }
    
    private void initCardImage(double width, double height) {
        Image cardImage = new Image("/resources/images/cards/meet_the_dean.png",width, height, true, true);
        cardImageView = new ImageView(cardImage);
    }
    
    private VBox createScoreDisplay(double x, double y) {
        initScoreBoard(x, y);
        Label scoreLabel = new Label("Score");
        scoreLabel.setFont(Font.font ("Verdana", 20));
        scoreLabel.setPrefHeight(20);
        VBox box = new VBox(2);
        box.getChildren().addAll(scoreLabel, scoreBoard);
        
        return box;
    }
    
    private void initScoreBoard(double width, double height) {
        scoreBoard = new ScoreBoard();
        scoreBoard.init(1);
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
        scoreBoard.addPlayer(player);

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
    
    public void updateScoreBoard() {
        scoreBoard.update();
    }
    
    public void disable() {
        move.setDisable(true);
        playCard.setDisable(true);
    }
    
    public void enable() {
        move.setDisable(false);
        playCard.setDisable(false);
    }
}
