package com.example.wiezen;

import android.os.Bundle;
import android.widget.TextView;

import com.logic.wiezen.Game;
import com.logic.wiezen.GameConfiguration;
import com.logic.wiezen.Player;
import com.logic.wiezen.Round;

import java.util.LinkedList;

public class HomeActivity extends AuthUserAppCompatActivity {
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        text = findViewById(R.id.welcomeText);

        text.setText(user.getUid());
    }

    private Game CreateGame(String ownerGuid, String... playerNames){
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
                ownerGuid,
                players,
                playerCount,
                config,
                startingRound);
    }
}
