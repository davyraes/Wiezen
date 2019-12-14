package com.logic.wiezen;

import java.util.Dictionary;
import java.util.List;

public abstract class PlayAble implements IPlayAble {
    private GameConfiguration configuration;
    public PlayAble(GameConfiguration configuration) {
        this.configuration = configuration;
    }

    protected GameConfiguration getConfiguration() {
        return configuration;
    }

    protected void OneVsThree(Dictionary<Player, Integer> scores, List<Player> players, List<Player> contestors, boolean winCondition, int pPP, boolean doubled){
        if(doubled && configuration.isDoubleAllWon()){
            pPP = pPP*2;
        }
        if(winCondition){
            for (Player winner : players) {
                scores.put(winner, scores.get(winner) + pPP);
            }
            for (Player loser : contestors) {
                scores.put(loser, scores.get(loser) - (pPP/3));
            }
        }else {
            for (Player winner : contestors) {
                scores.put(winner, scores.get(winner) + (pPP/3));
            }
            for (Player loser : players) {
                scores.put(loser, scores.get(loser) - pPP);
            }
        }
    }

    protected void TwoVsTwo(Dictionary<Player, Integer> scores, List<Player> players, List<Player> contestors, boolean winCondition, int pPP, boolean doubled){
        if(doubled && configuration.isDoubleAllWon()){
            pPP = pPP*2;
        }
        if(winCondition){
            for (Player winner : players) {
                scores.put(winner, scores.get(winner) + pPP);
            }
            for (Player loser : contestors) {
                scores.put(loser, scores.get(loser) - pPP);
            }
        }else {
            for (Player winner : contestors) {
                scores.put(winner, scores.get(winner) + pPP);
            }
            for (Player loser : players) {
                scores.put(loser, scores.get(loser) - pPP);
            }
        }
    }
}
