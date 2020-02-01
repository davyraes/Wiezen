package com.logic.wiezen;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {
    public Player() {
    }

    public String name;

    public Player(String name) {
        this.name = name;
    }

    protected Player(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
