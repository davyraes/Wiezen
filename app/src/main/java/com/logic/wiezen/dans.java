package com.logic.wiezen;

import java.util.Dictionary;
import java.util.List;

public class dans extends PlayAble {
    private int amount;

    public dans(GameConfiguration configuration, int amount) {
        super(configuration);
        this.amount = amount;
    }

    @Override
    public Dictionary<Player, Integer> Process(Dictionary<Player, Integer> beginScores, List<Player> players, List<Player> contestors, int winningHands) {
        Dictionary<Player, Integer> result = beginScores;
        int pPP;
        switch (amount){
            case 9:
                pPP = getConfiguration().getPointsForDans9();
                break;
            case 10:
                pPP = getConfiguration().getPointsForDans10();
                break;
            case 11:
                pPP = getConfiguration().getPointsForDans11();
                break;
            case 12:
                pPP = getConfiguration().getPointsForDans12();
                break;
            default:
                // throw error
                pPP = 0;
                break;
        }

        OneVsThree(result, players, contestors, winningHands >= this.amount, pPP, false);
        return result;
    }
}
