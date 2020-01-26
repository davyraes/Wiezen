package com.logic.wiezen;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    public ArrayList<Player> players;
    public int amountOfPlayers;
    public GameConfiguration configuration;
    public ArrayList<Round> rounds;
    public String user;

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public Game(
            ArrayList<Player> players,
            int amountOfPlayers,
            GameConfiguration configuration,
            ArrayList<Round> rounds,
            String user) {
        this.players = players;
        this.amountOfPlayers = amountOfPlayers;
        this.configuration = configuration;
        this.rounds = rounds;
        this.user = user;
    }

    public void AddRound(Round round){
        if (round != null){
            rounds.add( round);
        }
    }
}
