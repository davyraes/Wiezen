package com.logic.wiezen;

import java.util.Dictionary;
import java.util.List;

public class Round extends Thread {
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
}
