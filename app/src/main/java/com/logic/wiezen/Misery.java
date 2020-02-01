package com.logic.wiezen;

import java.util.ArrayList;
import java.util.Map;

public class Misery extends PlayAble {
    private boolean open;
    private boolean isWon;
    public Misery(GameConfiguration configuration, boolean open) {
        super(configuration);
        this.open = open;
    }

    public Misery(GameConfiguration configuration) {
        super(configuration);
    }

    @Override
    public Map<String, Integer> Process(Map<String, Integer> beginScores, ArrayList<Player> players, ArrayList<Player> contestors, int winningHands) throws Exception {
        Map<String, Integer> result = beginScores;
        int pPP;
        isWon = winningHands == 0;
        if (this.open){
            pPP = configuration.pointsForOpenMisery;
        }
        else{
            pPP = configuration.pointsForMisery;
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
