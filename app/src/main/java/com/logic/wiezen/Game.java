package com.logic.wiezen;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Game implements Serializable {
    private List<Player> players;
    private int amountOfPlayers;
    private GameConfiguration configuration;
    private LinkedList<Round> rounds;
    private String user;

    public LinkedList<Round> getRounds() {
        return rounds;
    }

    public Game(
            List<Player> players,
            int amountOfPlayers,
            GameConfiguration configuration,
            LinkedList<Round> rounds,
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
