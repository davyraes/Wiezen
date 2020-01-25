package com.logic.wiezen;

import java.util.Dictionary;
import java.util.List;

public class Misery extends PlayAble {
    private boolean open;
    private boolean isWon;
    public Misery(GameConfiguration configuration, boolean open) {
        super(configuration);
        this.open = open;
    }

    @Override
    public Dictionary<Player, Integer> Process(Dictionary<Player, Integer> beginScores, List<Player> players, List<Player> contestors, int winningHands) throws Exception {
        Dictionary<Player, Integer> result = beginScores;
        int pPP;
        isWon = winningHands == 0;
        if (this.open){
            pPP = getConfiguration().getPointsForOpenMisery();
        }
        else{
            pPP = getConfiguration().getPointsForMisery();
        }

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
