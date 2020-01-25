package com.logic.wiezen;

import java.util.Dictionary;
import java.util.List;

public class Start extends PlayAble {
    public Start(GameConfiguration configuration) {
        super(configuration);
    }

    @Override
    public Dictionary<Player, Integer> Process(Dictionary<Player, Integer> beginScores, List<Player> players, List<Player> contestors, int winningHands) throws Exception {
        return beginScores;
    }

    @Override
    public boolean IsWon() {
        return true;
    }
}
