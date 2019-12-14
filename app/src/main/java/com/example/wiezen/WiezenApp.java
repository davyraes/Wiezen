package com.example.wiezen;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class WiezenApp extends Application {
    private FirebaseApp firebaseApp;

    public WiezenApp(FirebaseApp firebaseApp) {
        this.firebaseApp = firebaseApp;
    }
}
