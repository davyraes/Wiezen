package com.example.wiezen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.logic.wiezen.Game;
import com.logic.wiezen.GameConfiguration;
import com.logic.wiezen.Player;
import com.logic.wiezen.Round;
import com.logic.wiezen.Start;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import androidx.annotation.NonNull;

public class PlayersActivity extends AuthUserAppCompatActivity {
    private static final String TAG = "PlayersActivity";

    private TextView player1;
    private TextView player2;
    private TextView player3;
    private TextView player4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        player1 = findViewById(R.id.playerName1Text);
        player2 = findViewById(R.id.playerName2Text);
        player3 = findViewById(R.id.playerName3Text);
        player4 = findViewById(R.id.playerName4Text);
    }

    public void OnSubmitBtnClick(View v){
        final String p1Name = player1.getText().toString();
        final String p2Name = player2.getText().toString();
        final String p3Name = player3.getText().toString();
        final String p4Name = player4.getText().toString();

        if(p1Name.isEmpty() || p2Name.isEmpty() || p3Name.isEmpty() || p4Name.isEmpty()){
            Toast.makeText(PlayersActivity.this, "PlayerName incorrect", Toast.LENGTH_SHORT).show();
        }

        Game newGame = CreateGame(new GameConfiguration(), p1Name, p2Name, p3Name, p4Name);

        SaveGameToDb(newGame)
                 .addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {
                         if(!task.isSuccessful()){
                             Toast.makeText(PlayersActivity.this, " saving failed", Toast.LENGTH_SHORT).show();
                         }else{
                             Intent intent = new Intent(PlayersActivity.this, OverViewActivity.class);
                             startActivity(intent);
                         }
                     }
                 });

    }

    private Game CreateGame(GameConfiguration config, String... playerNames){
        int playerCount = playerNames.length;
        if (playerCount < 4 || playerCount > 5){
            /// throw an error here
        }

        /// Make Players for each playername
        ArrayList<Player> players = new ArrayList<>();
        Map<String, Integer> start = new Hashtable<>();
        for (String name : playerNames) {
            Player player = new Player(name);
            players.add(player);
            start.put(player.name, 0);
        }

        /// Create a new starting round
        ArrayList<Round> startingRound = new ArrayList<>();
        try {
            startingRound.add(new Round(start, null , null, null, new Start(config), 0));
        } catch (Exception e) {
            Log.e(TAG, "CreateGame: startinground", e);
        }

        return new Game(
                players,
                playerCount,
                config,
                startingRound,
                user.getUid());
    }

    @Override
    protected Boolean NeedsAuth() {
        return true;
    }
}
