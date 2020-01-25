package com.logic.wiezen;

import java.util.Dictionary;
import java.util.List;

public class Regular extends PlayAble {
    private boolean isWon;

    public Regular(GameConfiguration configuration) {
        super(configuration);
    }

    @Override
    public Dictionary<Player, Integer> Process(Dictionary<Player, Integer> beginScores, List<Player> players, List<Player> contestors, int winningHands) {
        if(players.isEmpty() || players.size() > 2) {
            // throw an error
        }

        Dictionary<Player, Integer>result = beginScores;

        if (players.size() == 1){
            int ppp = (getConfiguration().getPointsForRegular() + Math.abs(winningHands - getConfiguration().getHandsRegular1P())) * 3;
            isWon = winningHands >= getConfiguration().getHandsRegular1P();
            OneVsThree(result, players, contestors, isWon, ppp, winningHands == 13);
        }
        else if (players.size() == 2){
            int ppp = getConfiguration().getPointsForRegular() + Math.abs(winningHands - getConfiguration().getHandsRegular2P());
            isWon = winningHands >= getConfiguration().getHandsRegular2P();
            TwoVsTwo(result, players, contestors, isWon, ppp, winningHands == 13);
        }

        return result;
    }

    @Override
    public boolean IsWon() {
        return isWon;
    }
}
