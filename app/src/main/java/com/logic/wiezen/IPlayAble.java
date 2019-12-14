package com.logic.wiezen;

import java.util.Dictionary;
import java.util.List;

public interface IPlayAble {
    Dictionary<Player, Integer> Process(
            Dictionary<Player, Integer> beginScores,
            List<Player> players,
            List<Player> contestors,
            int winningHands) throws Exception;
}
