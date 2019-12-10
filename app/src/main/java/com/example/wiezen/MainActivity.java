package com.example.wiezen;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.logic.wiezen.Game;
import com.logic.wiezen.GameConfiguration;
import com.logic.wiezen.Player;
import com.logic.wiezen.Round;

import java.util.LinkedList;

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
