package com.logic.wiezen;

import android.os.Parcel;
import android.os.Parcelable;

public class GameConfiguration implements Parcelable {
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

    protected GameConfiguration(Parcel in) {
        doubleAllWon = in.readByte() != 0;
        pointsForRegular = in.readInt();
        pointsForOverStack = in.readInt();
        pointsForTroul = in.readInt();
        pointsForDans9 = in.readInt();
        pointsForDans10 = in.readInt();
        pointsForDans11 = in.readInt();
        pointsForDans12 = in.readInt();
        pointsForMisery = in.readInt();
        pointsForOpenMisery = in.readInt();
        pointsForSolo = in.readInt();
        pointsForSoloSlim = in.readInt();
        handsRegular1P = in.readInt();
        handsRegular2P = in.readInt();
        handsTroul = in.readInt();
    }

    public static final Creator<GameConfiguration> CREATOR = new Creator<GameConfiguration>() {
        @Override
        public GameConfiguration createFromParcel(Parcel in) {
            return new GameConfiguration(in);
        }

        @Override
        public GameConfiguration[] newArray(int size) {
            return new GameConfiguration[size];
        }
    };

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

    public int getHandsRegular1P() {
        return handsRegular1P;
    }

    public int getHandsRegular2P() {
        return handsRegular2P;
    }

    public int getHandsTroul() {
        return handsTroul;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (doubleAllWon ? 1 : 0));
        dest.writeInt(pointsForRegular);
        dest.writeInt(pointsForOverStack);
        dest.writeInt(pointsForTroul);
        dest.writeInt(pointsForDans9);
        dest.writeInt(pointsForDans10);
        dest.writeInt(pointsForDans11);
        dest.writeInt(pointsForDans12);
        dest.writeInt(pointsForMisery);
        dest.writeInt(pointsForOpenMisery);
        dest.writeInt(pointsForSolo);
        dest.writeInt(pointsForSoloSlim);
        dest.writeInt(handsRegular1P);
        dest.writeInt(handsRegular2P);
        dest.writeInt(handsTroul);
    }
}
