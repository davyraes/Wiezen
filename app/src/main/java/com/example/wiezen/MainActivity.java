package com.example.wiezen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private Game CreateGame(String... playerNames){
        int playerCount = playerNames.length;
        if (playerCount < 4 || playerCount > 5){
            /// throw an error here
        }

        /// Make Players for each playername
        LinkedList<Player> players = new LinkedList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        
        /// Import the config from database
        GameConfiguration config = new GameConfiguration();
        
        /// Create a new starting round
        LinkedList<Round> startingRound = new LinkedList<>();

        return new Game(
                players,
                playerCount,
                config,
                startingRound);
    }
}
