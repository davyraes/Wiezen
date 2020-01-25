package com.logic.wiezen;

import java.util.Dictionary;
import java.util.List;

public class Round{
    private Dictionary<Player, Integer> playerScores;
    private List<Player> players;
    private List<Player> contestors;
    private Player dealer;
    private IPlayAble play;
    private int hands;

    public Round(Dictionary<Player, Integer> playerScores, List<Player> players, List<Player> contestors, Player dealer, IPlayAble play, int hands) throws Exception {
        this.dealer = dealer;
        this.play = play;
        this.hands = hands;
        this.players = players;
        this.contestors = contestors;
        this.playerScores = play.Process(playerScores, players, contestors, hands);
    }

    public Dictionary<Player, Integer> getPlayerScores() {
        return playerScores;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getDealer() {
        return dealer;
    }

    public IPlayAble getPlay() {
        return play;
    }

    public int getHands() {
        return hands;
    }

    public List<Player> getContestors() {
        return contestors;
    }

    public Boolean IsWon() {
        return play.IsWon();
    }
}
