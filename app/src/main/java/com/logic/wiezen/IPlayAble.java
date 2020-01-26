package com.logic.wiezen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public interface IPlayAble extends Serializable {
    Map<String, Integer> Process(
            Map<String, Integer> beginScores,
            ArrayList<Player> players,
            ArrayList<Player> contestors,
            int winningHands) throws Exception;

    boolean IsWon();
}
