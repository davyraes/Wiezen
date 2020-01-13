package com.example.wiezen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.logic.wiezen.Game;
import com.logic.wiezen.GameConfiguration;
import com.logic.wiezen.Player;
import com.logic.wiezen.Round;

import java.util.LinkedList;

public class HomeActivity extends AuthUserAppCompatActivity {
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        continueButton = findViewById(R.id.continueGameButton);
        continueButton.setEnabled(game != null);
    }

    public void OnNewGameButtonClick(View v){

    }

    public void OnContinueGameButtonClick(View v){

    }

    public void OnLogOutButtonClick(View v){

        Intent toLogin = new Intent(HomeActivity.this, LoginActivity.class);
        mFirebaseAuth.signOut();
        startActivity(toLogin);
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

    private void GetGameFromDB()
    {
        DocumentReference docRef = db.collection("Games").document(user.getUid());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Game game = documentSnapshot.toObject(Game.class);
            }
        });
    }

    @Override
    protected Boolean NeedsAuth() {
        return true;
    }
}
