package com.logic.wiezen;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Round implements Parcelable {
    public int id;
    public Map<String, Integer> playerScores;
    public ArrayList<Player> players;
    public ArrayList<Player> contestors;
    public Player dealer;
    public PlayAbleEnum play;
    public int hands;
    public boolean IsWon;

    protected Round(Parcel in) {
        players = in.createTypedArrayList(Player.CREATOR);
        contestors = in.createTypedArrayList(Player.CREATOR);
        dealer = in.readParcelable(Player.class.getClassLoader());
        hands = in.readInt();
        IsWon = in.readByte() != 0;
    }

    public static final Creator<Round> CREATOR = new Creator<Round>() {
        @Override
        public Round createFromParcel(Parcel in) {
            return new Round(in);
        }

        @Override
        public Round[] newArray(int size) {
            return new Round[size];
        }
    };

    public Map<String, Integer> getPlayerScores() {
        return playerScores;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Player> getContestors() {
        return contestors;
    }

    public Player getDealer() {
        return dealer;
    }

    public PlayAbleEnum getPlay() {
        return play;
    }

    public int getHands() {
        return hands;
    }

    public Round() {
    }

    public Round(
            int id,
            Map<String, Integer> playerScores,
            ArrayList<Player> players,
            ArrayList<Player> contestors,
            Player dealer,
            PlayAbleEnum play,
            int hands,
            GameConfiguration config) throws Exception {
        this.id = id;
        this.dealer = dealer;
        this.play = play;
        this.hands = hands;
        this.players = players;
        this.contestors = contestors;
        IPlayAble playable;
        switch (play){
            case START:
                playable = new Start(config);
                break;
            case REGULAR:
                playable = new Regular(config);
                break;
            case DANS_9:
                playable = new dans(config,9);
                break;
            case DANS_10:
                playable = new dans(config, 10);
                break;
            case DANS_11:
                playable = new dans(config, 11);
                break;
            case DANS_12:
                playable = new dans(config, 12);
                break;
            case SOLO:
                playable = new Solo(config, false);
                break;
            case SOLO_SLIM:
                playable = new Solo(config, true);
                break;
            case TROUL:
                playable = new Troul(config);
                break;
            case MISERY:
                playable = new Misery(config, false);
                break;
            case MISERY_OPEN:
                playable = new Misery(config, true);
                break;
            default:
                playable = new Start(config);
                break;
        }

        HashMap<String, Integer>new_map = new HashMap<>();
        new_map.putAll(playerScores);

        this.playerScores = playable.Process(new_map, players, contestors, hands);
        this.IsWon = playable.IsWon();
    }
    public boolean IsWon(){
        return IsWon();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(players);
        dest.writeTypedList(contestors);
        dest.writeParcelable(dealer, flags);
        dest.writeInt(hands);
        dest.writeByte((byte) (IsWon ? 1 : 0));
    }
}
