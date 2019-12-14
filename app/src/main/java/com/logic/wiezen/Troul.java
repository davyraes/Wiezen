package com.logic.wiezen;

import java.util.Dictionary;
import java.util.List;

public class Troul extends PlayAble {
    public Troul(GameConfiguration configuration) {
        super(configuration);
    }

    @Override
    public Dictionary<Player, Integer> Process(Dictionary<Player, Integer> beginScores, List<Player> players, List<Player> contestors, int winningHands) throws Exception {
        if(players.size() != 2){
            throw new Exception("Wrong amount of players");
        }
        Dictionary<Player, Integer> result = beginScores;
        int pPP = getConfiguration().getPointsForTroul() + (Math.abs(winningHands - getConfiguration().getPointsForOverStack())*2);
        TwoVsTwo(result, players, contestors, winningHands >= getConfiguration().getHandsTroul(), pPP, winningHands == 13 );
        return result;
    }
}
