package com.example.wiezen;

import java.util.Dictionary;
import java.util.HashSet;

public interface IPlayAble {
    Dictionary<Player, Integer> Process(
            Dictionary<Player, Integer> beginScores,
            HashSet<Player> players,
            HashSet<Player> contestors,
            boolean win);
}
