package com.logic.wiezen;

import java.util.ArrayList;
import java.util.Map;

public class Regular extends PlayAble {
    private boolean isWon;

    public Regular(GameConfiguration configuration) {
        super(configuration);
    }

    public Regular() {

    }

    @Override
    public Map<String, Integer> Process(Map<String, Integer> beginScores, ArrayList<Player> players, ArrayList<Player> contestors, int winningHands) {
        if(players.isEmpty() || players.size() > 2) {
            // throw an error
        }

        Map<String, Integer>result = beginScores;

        if (players.size() == 1){
            int ppp = (configuration.pointsForRegular + Math.abs(winningHands - configuration.handsRegular1P)) * 3;
            isWon = winningHands >= configuration.handsRegular1P;
            OneVsThree(result, players, contestors, isWon, ppp, winningHands == 13);
        }
        else if (players.size() == 2){
            int ppp = configuration.pointsForRegular + Math.abs(winningHands - configuration.handsRegular2P);
            isWon = winningHands >= configuration.handsRegular2P;
            TwoVsTwo(result, players, contestors, isWon, ppp, winningHands == 13);
        }

        return result;
    }

    @Override
    public boolean IsWon() {
        return isWon;
    }
}
