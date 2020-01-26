package com.logic.wiezen;

import java.io.Serializable;

public class GameConfiguration implements Serializable {
    public boolean doubleAllWon;
    public int pointsForRegular;
    public int pointsForOverStack;
    public int pointsForTroul;
    public int pointsForDans9;
    public int pointsForDans10;
    public int pointsForDans11;
    public int pointsForDans12;
    public int pointsForMisery;
    public int pointsForOpenMisery;
    public int pointsForSolo;
    public int pointsForSoloSlim;
    public int handsRegular1P;
    public int handsRegular2P;
    public int handsTroul;


    public GameConfiguration() {
        this.doubleAllWon = true;
        this.pointsForRegular = 1;
        this.pointsForOverStack = 1;
        this.pointsForTroul = this.pointsForRegular * 2;
        this.pointsForDans9 = 9;
        this.pointsForDans10 = 12;
        this.pointsForDans11 = 15;
        this.pointsForDans12 = 18;
        this.pointsForMisery = 12;
        this.pointsForOpenMisery = 24;
        this.pointsForSolo = 75;
        this.pointsForSoloSlim = 150;
        this.handsRegular1P = 5;
        this.handsRegular2P = 8;
        this.handsTroul = 8;
    }
}
