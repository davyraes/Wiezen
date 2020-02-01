package com.logic.wiezen;

import java.util.ArrayList;
import java.util.Map;

public class dans extends PlayAble {
    private int amount;
    private boolean isWon;

    public dans(GameConfiguration configuration, int amount) {
        super(configuration);
        this.amount = amount;
    }

    public dans(GameConfiguration configuration) {
        super(configuration);
    }

    @Override
    public Map<String, Integer> Process(Map<String, Integer> beginScores, ArrayList<Player> players, ArrayList<Player> contestors, int winningHands) {
        Map<String, Integer> result = beginScores;
        int pPP;
        switch (amount){
            case 9:
                pPP = configuration.pointsForDans9;
                break;
            case 10:
                pPP = configuration.pointsForDans10;
                break;
            case 11:
                pPP = configuration.pointsForDans11;
                break;
            case 12:
                pPP = configuration.pointsForDans12;
                break;
            default:
                // throw error
                pPP = 0;
                break;
        }
        isWon = winningHands >= this.amount;

        OneVsThree(result, players, contestors, isWon, pPP, false);
        return result;
    }

    @Override
    public boolean IsWon() {
        return isWon;
    }
}
