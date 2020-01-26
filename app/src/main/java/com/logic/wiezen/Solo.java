package com.logic.wiezen;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;

public class Solo extends PlayAble {
    private boolean slim;
    public Solo(GameConfiguration configuration, boolean slim) {
        super(configuration);
        this.slim = slim;
    }
    private boolean isWon;

    @Override
    public Map<String, Integer> Process(Map<String, Integer> beginScores, ArrayList<Player> players, ArrayList<Player> contestors, int winningHands) throws Exception {
        Map<String, Integer> result = beginScores;
        int pPP;
        if (this.slim){
            pPP = configuration.pointsForSoloSlim;
        }
        else{
            pPP = configuration.pointsForSolo;
        }

        isWon = winningHands == 13;
        if (players.size() == 1){
            OneVsThree(result, players, contestors, isWon, pPP, false);
        }
        else {
            throw new Exception("Unsupported amount of players");
        }

        return result;
    }

    @Override
    public boolean IsWon() {
        return isWon;
    }
}
