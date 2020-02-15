package com.example.wiezen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.logic.wiezen.Game;
import com.logic.wiezen.GameConfiguration;
import com.logic.wiezen.PlayAbleEnum;
import com.logic.wiezen.Player;
import com.logic.wiezen.Round;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;

public class PlayersActivity extends AuthUserAppCompatActivity {
    private static final String TAG = "PlayersActivity";
    public static final String P1_KEY = "P1";
    private static final String P2_KEY = "P2";
    private static final String P3_KEY = "P3";
    private static final String P4_KEY = "P4";

    private TextView player1;
    private TextView player2;
    private TextView player3;
    private TextView player4;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        player1 = findViewById(R.id.playerName1Text);
        player2 = findViewById(R.id.playerName2Text);
        player3 = findViewById(R.id.playerName3Text);
        player4 = findViewById(R.id.playerName4Text);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(P1_KEY)) {
                player1.setText(savedInstanceState.getString(P1_KEY));
            }
            if (savedInstanceState.containsKey(P2_KEY)) {
                player1.setText(savedInstanceState.getString(P2_KEY));
            }
            if (savedInstanceState.containsKey(P3_KEY)) {
                player1.setText(savedInstanceState.getString(P3_KEY));
            }
            if (savedInstanceState.containsKey(P4_KEY)) {
                player1.setText(savedInstanceState.getString(P4_KEY));
            }
        } else {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            if (sharedPreferences.contains(getString(R.string.EnableP1Key))) {
                if (sharedPreferences.getBoolean(getString(R.string.EnableP1Key), false)) {

                    if (sharedPreferences.contains(getString(R.string.P1Key))) {
                        player1.setText(sharedPreferences.getString(getString(R.string.P1Key), ""));
                        player1.setEnabled(false);
                        player2.requestFocus();
                    }
                }
            }
        }
    }

    public void OnSubmitBtnClick(View v) {
        final String p1Name = player1.getText().toString();
        final String p2Name = player2.getText().toString();
        final String p3Name = player3.getText().toString();
        final String p4Name = player4.getText().toString();

        if (p1Name.isEmpty() || p2Name.isEmpty() || p3Name.isEmpty() || p4Name.isEmpty()) {
            Toast.makeText(PlayersActivity.this, "PlayerName incorrect", Toast.LENGTH_SHORT).show();
        }

        Game newGame = CreateGame(new GameConfiguration(), p1Name, p2Name, p3Name, p4Name);

        SaveGameToDb(newGame)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(PlayersActivity.this, " saving failed", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(PlayersActivity.this, OverViewActivity.class);
                            startActivity(intent);
                        }
                    }
                });

    }

    private Game CreateGame(GameConfiguration config, String... playerNames) {
        int playerCount = playerNames.length;
        if (playerCount < 4 || playerCount > 5) {
            Log.e(TAG, "CreateGame: wrong amount of players", new Exception());
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
            startingRound.add(new Round(start, players, null, null, PlayAbleEnum.START, 0, config));
        } catch (Exception e) {
            Log.e(TAG, "CreateGame: Can t create game", e);
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        final String p1Name = player1.getText().toString();
        final String p2Name = player2.getText().toString();
        final String p3Name = player3.getText().toString();
        final String p4Name = player4.getText().toString();

        outState.putString(P1_KEY, p1Name);
        outState.putString(P2_KEY, p2Name);
        outState.putString(P3_KEY, p3Name);
        outState.putString(P4_KEY, p4Name);
    }
}
