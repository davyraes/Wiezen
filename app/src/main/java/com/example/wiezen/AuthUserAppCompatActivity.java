package com.example.wiezen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.logic.wiezen.Game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public abstract class AuthUserAppCompatActivity extends AppCompatActivity {
    private static final String TAG = "AuthUserAppCompatActivi";
    protected FirebaseAuth mFirebaseAuth;
    protected FirebaseAuth.AuthStateListener mAuthStateListener;
    protected FirebaseUser user;
    protected FirebaseFirestore db;
    protected DocumentReference gameReference;
    protected Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();

        mFirebaseAuth = FirebaseAuth.getInstance();
        if(NeedsAuth()){
            user = mFirebaseAuth.getCurrentUser();
            gameReference = db.collection("Games").document(user.getUid());
        }

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = mFirebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(AuthUserAppCompatActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                    Intent toHome = new Intent(AuthUserAppCompatActivity.this, HomeActivity.class);
                    startActivity(toHome);
                }
            }
        };
    }

    protected abstract Boolean NeedsAuth();

    protected Task<DocumentSnapshot> GetGameFromDB() {
        return gameReference
                .get();
    }

    protected Task<Void> SaveGameToDb(Game game){
        return gameReference.set(game);
    }
}
