package com.logic.wiezen;

import java.util.ArrayList;
import java.util.Map;

public class Round{
    public Map<String, Integer> playerScores;
    public ArrayList<Player> players;
    public ArrayList<Player> contestors;
    public Player dealer;
    public IPlayAble play;
    public int hands;

    public Round(Map<String, Integer> playerScores, ArrayList<Player> players, ArrayList<Player> contestors, Player dealer, IPlayAble play, int hands) throws Exception {
        this.dealer = dealer;
        this.play = play;
        this.hands = hands;
        this.players = players;
        this.contestors = contestors;
        this.playerScores = play.Process(playerScores, players, contestors, hands);
    }

    public boolean IsWon(){
        return play.IsWon();
    }
}
