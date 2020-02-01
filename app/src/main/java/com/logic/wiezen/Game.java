package com.logic.wiezen;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Game implements Parcelable {
    public ArrayList<Player> players;
    public int amountOfPlayers;
    public GameConfiguration configuration;

    protected Game(Parcel in) {
        amountOfPlayers = in.readInt();
        user = in.readString();
        configuration = in.readParcelable(GameConfiguration.class.getClassLoader());
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getAmountOfPlayers() {
        return amountOfPlayers;
    }

    public GameConfiguration getConfiguration() {
        return configuration;
    }

    public String getUser() {
        return user;
    }

    public ArrayList<Round> rounds;

    public Game() {
    }

    public String user;

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public Game(
            ArrayList<Player> players,
            int amountOfPlayers,
            GameConfiguration configuration,
            ArrayList<Round> rounds,
            String user) {
        this.players = players;
        this.amountOfPlayers = amountOfPlayers;
        this.configuration = configuration;
        this.rounds = rounds;
        this.user = user;
    }

    public void AddRound(Round round){
        if (round != null){
            rounds.add( round);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(amountOfPlayers);
        dest.writeString(user);
        dest.writeParcelable(configuration, flags);
    }
}
