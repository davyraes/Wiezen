package com.logic.wiezen;

import java.util.LinkedList;
import java.util.List;

public class Game {
    private String ownerGuid;
    private List<Player> players;
    private int amountOfPlayers;
    private GameConfiguration configuration;
    private LinkedList<Round> rounds;

    public Game(
            String ownerGuid,
            List<Player> players,
            int amountOfPlayers,
            GameConfiguration configuration,
            LinkedList<Round> rounds) {
        this.ownerGuid = ownerGuid;
        this.players = players;
        this.amountOfPlayers = amountOfPlayers;
        this.configuration = configuration;
        this.rounds = rounds;
    }

    public void AddRound(Round round){
        if (round != null){
            rounds.add( round);
        }
    }
}
