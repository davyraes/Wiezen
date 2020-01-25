package com.logic.wiezen;

import java.util.Dictionary;
import java.util.List;

public class Solo extends PlayAble {
    private boolean slim;
    public Solo(GameConfiguration configuration, boolean slim) {
        super(configuration);
        this.slim = slim;
    }
    private boolean isWon;

    @Override
    public Dictionary<Player, Integer> Process(Dictionary<Player, Integer> beginScores, List<Player> players, List<Player> contestors, int winningHands) throws Exception {
        Dictionary<Player, Integer> result = beginScores;
        int pPP;
        if (this.slim){
            pPP = getConfiguration().getPointsForSoloSlim();
        }
        else{
            pPP = getConfiguration().getPointsForSolo();
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
