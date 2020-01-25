package com.example.wiezen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.logic.wiezen.Game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public abstract class AuthUserAppCompatActivity extends AppCompatActivity {
    protected FirebaseAuth mFirebaseAuth;
    protected FirebaseAuth.AuthStateListener mAuthStateListener;
    protected FirebaseUser user;
    protected FirebaseFirestore db;
    protected CollectionReference gamesCollection;
    protected Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirebaseAuth = FirebaseAuth.getInstance();
        if(NeedsAuth()){
            db = FirebaseFirestore.getInstance();
            user = mFirebaseAuth.getCurrentUser();
            gamesCollection = db.collection("Games");
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

    protected void GetGameFromDB()
    {
        DocumentReference docRef = gamesCollection.document(user.getUid());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Game game = documentSnapshot.toObject(Game.class);
            }
        });
    }
}
