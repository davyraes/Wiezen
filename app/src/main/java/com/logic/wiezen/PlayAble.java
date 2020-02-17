package com.logic.wiezen;

import java.util.ArrayList;
import java.util.Map;

public abstract class PlayAble implements IPlayAble {
    public GameConfiguration configuration;
    public PlayAble(GameConfiguration configuration) {
        this.configuration = configuration;
    }

    public PlayAble() {
    }

    protected void OneVsThree(Map<String, Integer> scores, ArrayList<Player> players, ArrayList<Player> contestors, boolean winCondition, int pPP, boolean doubled){
        if(doubled && configuration.doubleAllWon){
            pPP = pPP*2;
        }
        if(winCondition){
            for (Player winner : players) {
                scores.put(winner.name, scores.get(winner.name) + pPP);
            }
            for (Player loser : contestors) {
                scores.put(loser.name, scores.get(loser.name) - (pPP/3));
            }
        }else {
            for (Player winner : contestors) {
                scores.put(winner.name, scores.get(winner.name) + (pPP/3));
            }
            for (Player loser : players) {
                scores.put(loser.name, scores.get(loser.name) - pPP);
            }
        }
    }

    protected void TwoVsTwo(Map<String, Integer> scores, ArrayList<Player> players, ArrayList<Player> contestors, boolean winCondition, int pPP, boolean doubled){
        if(doubled && configuration.doubleAllWon){
            pPP = pPP*2;
        }
        if(winCondition){
            for (Player winner : players) {
                scores.put(winner.name, scores.get(winner.name) + pPP);
            }
            for (Player loser : contestors) {
                scores.put(loser.name, scores.get(loser.name) - pPP);
            }
        }else {
            for (Player winner : contestors) {
                scores.put(winner.name, scores.get(winner.name) + pPP);
            }
            for (Player loser : players) {
                scores.put(loser.name, scores.get(loser.name) - pPP);
            }
        }
    }
}
