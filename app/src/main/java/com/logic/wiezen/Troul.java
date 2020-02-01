package com.logic.wiezen;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;

public class Troul extends PlayAble {
    private boolean isWon;
    public Troul(GameConfiguration configuration) {
        super(configuration);
    }

    public Troul(){
    }

    @Override
    public Map<String, Integer> Process(Map<String, Integer> beginScores, ArrayList<Player> players, ArrayList<Player> contestors, int winningHands) throws Exception {
        if(players.size() != 2){
            throw new Exception("Wrong amount of players");
        }
        Map<String, Integer> result = beginScores;
        int pPP = configuration.pointsForTroul + (Math.abs(winningHands - configuration.pointsForOverStack)*2);
        isWon = winningHands >= configuration.handsTroul;
        TwoVsTwo(result, players, contestors, isWon, pPP, winningHands == 13 );
        return result;
    }

    @Override
    public boolean IsWon() {
        return isWon;
    }
}
