package com.logic.wiezen;

import java.util.Dictionary;
import java.util.HashSet;

public class Round extends Thread {
    private Dictionary<Player, Integer> playerScores;
    private HashSet<Player> players;
    private HashSet<Player> contestors;
    private Player dealer;
    private IPlayAble play;
    private int nrOfWins;

    public Round(Dictionary<Player, Integer> playerScores, HashSet<Player> players, HashSet<Player> contestors, Player dealer, IPlayAble play, int wins) {
        this.dealer = dealer;
        this.play = play;
        this.nrOfWins = wins;
        this.players = players;
        this.contestors = contestors;
        this.playerScores = play.Process(playerScores, players, contestors, wins);
    }
}