package com.logic.wiezen;

import java.io.Serializable;
import java.security.InvalidParameterException;

public class GameConfiguration implements Serializable {
    private boolean doubleAllWon;
    private int pointsForRegular;
    private int pointsForOverStack;
    private int pointsForTroul;
    private int pointsForDans9;
    private int pointsForDans10;
    private int pointsForDans11;
    private int pointsForDans12;
    private int pointsForMisery;
    private int pointsForOpenMisery;
    private int pointsForSolo;
    private int pointsForSoloSlim;
    private int handsRegular1P;
    private int handsRegular2P;
    private int handsTroul;


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

    public int getHandsRegular1P() {
        return handsRegular1P;
    }

    public void setHandsRegular1P(int handsRegular1P) {
        this.handsRegular1P = handsRegular1P;
    }

    public int getHandsRegular2P() {
        return handsRegular2P;
    }

    public void setHandsRegular2P(int handsRegular2P) {
        this.handsRegular2P = handsRegular2P;
    }

    public int getHandsTroul() {
        return handsTroul;
    }

    public void setDoubleAllWon(boolean doubleAllWon) {
        this.doubleAllWon = doubleAllWon;
    }

    public void setPointsForRegular(int pointsForRegular) {
        this.pointsForRegular = pointsForRegular;
    }

    public void setPointsForOverStack(int pointsForOverStack) {
        this.pointsForOverStack = pointsForOverStack;
    }

    public void setPointsForTroul(int pointsForTroul) {
        this.pointsForTroul = IsDividableBy2(pointsForTroul);
    }

    public void setPointsForDans9(int pointsForDans9) {
        this.pointsForDans9 = IsDividableBy3(pointsForDans9);
    }

    public void setPointsForDans10(int pointsForDans10) {
        this.pointsForDans10 = IsDividableBy3(pointsForDans10);
    }

    public void setPointsForDans11(int pointsForDans11) {
        this.pointsForDans11 = IsDividableBy3(pointsForDans11);
    }

    public void setPointsForDans12(int pointsForDans12) {
        this.pointsForDans12 = IsDividableBy3(pointsForDans12);
    }

    public void setPointsForMisery(int pointsForMisery) {
        this.pointsForMisery = IsDividableBy2(IsDividableBy3(pointsForMisery));
    }

    public void setPointsForOpenMisery(int pointsForOpenMisery) {
        this.pointsForOpenMisery = IsDividableBy2(IsDividableBy3(pointsForOpenMisery));
    }

    public void setPointsForSolo(int pointsForSolo) {
        this.pointsForSolo = IsDividableBy3(pointsForSolo);
    }

    public void setPointsForSoloSlim(int pointsForSoloSlim) {
        this.pointsForSoloSlim =  IsDividableBy3(pointsForSoloSlim);
    }

    private int IsDividableBy3(int value){
        if (value % 3 == 0) {
            return value;
        } else {
            throw new InvalidParameterException("Input should be dividable by 3");
        }
    }

    private int IsDividableBy2(int value){
        if (value % 2 == 0) {
            return value;
        } else {
            throw new InvalidParameterException("Input should be dividable by 2");
        }
    }

    public boolean isDoubleAllWon() {
        return doubleAllWon;
    }

    public int getPointsForRegular() {
        return pointsForRegular;
    }

    public int getPointsForOverStack() {
        return pointsForOverStack;
    }

    public int getPointsForTroul() {
        return pointsForTroul;
    }

    public int getPointsForDans9() {
        return pointsForDans9;
    }

    public int getPointsForDans10() {
        return pointsForDans10;
    }

    public int getPointsForDans11() {
        return pointsForDans11;
    }

    public int getPointsForDans12() {
        return pointsForDans12;
    }

    public int getPointsForMisery() {
        return pointsForMisery;
    }

    public int getPointsForOpenMisery() {
        return pointsForOpenMisery;
    }

    public int getPointsForSolo() {
        return pointsForSolo;
    }

    public int getPointsForSoloSlim() {
        return pointsForSoloSlim;
    }
}
