package com.logic.wiezen;

import java.util.ArrayList;
import java.util.Map;

public class Start extends PlayAble {
    public Start(GameConfiguration configuration) {
        super(configuration);
    }

    public Start(){
    }
    @Override
    public Map<String, Integer> Process(Map<String, Integer> beginScores, ArrayList<Player> players, ArrayList<Player> contestors, int winningHands) throws Exception {
        return beginScores;
    }

    @Override
    public boolean IsWon() {
        return true;
    }
}
