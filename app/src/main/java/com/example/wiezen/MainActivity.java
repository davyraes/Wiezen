package com.example.wiezen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.logic.wiezen.Game;
import com.logic.wiezen.GameConfiguration;
import com.logic.wiezen.Player;
import com.logic.wiezen.Round;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private TextView eMailTextView;
    private TextView passWordTextView;
    private Button signUpButton;
    private Button toLoginButton;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        signUpButton = findViewById(R.id.SignUpBtn);
        toLoginButton = findViewById(R.id.ToLoginButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eMailTextView = v.findViewById(R.id.SignUpEmailTextbox);
                passWordTextView = v.findViewById(R.id.SignUpPassWordTextBox);

                String email = eMailTextView.getText().toString();
                String pwd = passWordTextView.getText().toString();
                if (email.isEmpty()){
                    eMailTextView.setError("Email missing");
                    eMailTextView.requestFocus();
                }
                else if (pwd.isEmpty()){
                    passWordTextView.setError("password Empty");
                    eMailTextView.requestFocus();
                }

                mFirebaseAuth
                        .createUserWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(MainActivity.this, "Signup unsuccesfull", Toast.LENGTH_SHORT).show();
                                }else{
                                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                }
                            }
                        });
            }
        });

        toLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
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
