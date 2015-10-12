package com.jvn.degreespree.models;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by john on 10/10/15.
 */
public class GameSettings {

    ArrayList<Player> players;

    public GameSettings(Player player1, Player player2, Player player3) {
        players = new ArrayList<>(3);
        players.add(player1);
        players.add(player2);
        players.add(player3);

        Collections.shuffle(players);

    }

    public GameSettings() {
        players = new ArrayList<>(3);
        Player player1 = new HumanPlayer("John");
        Player player2 = new ComputerPlayer("Cybord");
        Player player3 = new ComputerPlayer("CatBot");

        players.add(player1);
        players.add(player2);
        players.add(player3);

        Collections.shuffle(players);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}

