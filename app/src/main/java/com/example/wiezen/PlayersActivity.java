package com.example.wiezen;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.logic.wiezen.Game;
import com.logic.wiezen.GameConfiguration;
import com.logic.wiezen.Player;
import com.logic.wiezen.Round;

import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;

public class PlayersActivity extends AuthUserAppCompatActivity {
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
        final List<String> players = new LinkedList<>();

        final String p1Name = player1.getText().toString();
        final String p2Name = player2.getText().toString();
        final String p3Name = player3.getText().toString();
        final String p4Name = player4.getText().toString();

        if(p1Name.isEmpty() || p2Name.isEmpty() || p3Name.isEmpty() || p4Name.isEmpty()){
            Toast.makeText(PlayersActivity.this, "PlayerName incorrect", Toast.LENGTH_SHORT).show();
        }

        DocumentReference docRef = db.collection("Config").document(user.getUid());
        docRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        GameConfiguration config = documentSnapshot.toObject(GameConfiguration.class);
                        db
                                .collection("Games")
                                .add(
                                        CreateGame(
                                                config,
                                                p1Name,
                                                p2Name,
                                                p3Name,
                                                p4Name));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(user.getUid(),"New game needs gameconfig", e);
                    }
                });
    }

    private Game CreateGame(GameConfiguration config, String... playerNames){
        int playerCount = playerNames.length;
        if (playerCount < 4 || playerCount > 5){
            /// throw an error here
        }

        /// Make Players for each playername
        LinkedList<Player> players = new LinkedList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }

        /// Create a new starting round
        LinkedList<Round> startingRound = new LinkedList<>();

        return new Game(
                players,
                playerCount,
                config,
                startingRound);
    }

    @Override
    protected Boolean NeedsAuth() {
        return true;
    }
}
