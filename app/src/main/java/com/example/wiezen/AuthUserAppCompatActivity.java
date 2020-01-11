package com.example.wiezen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public abstract class AuthUserAppCompatActivity extends AppCompatActivity {
    protected FirebaseAuth mFirebaseAuth;
    protected FirebaseAuth.AuthStateListener mAuthStateListener;
    protected FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirebaseAuth = FirebaseAuth.getInstance();
        user = mFirebaseAuth.getCurrentUser();

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
}
