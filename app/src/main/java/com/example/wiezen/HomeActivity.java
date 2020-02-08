package com.example.wiezen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import androidx.annotation.NonNull;

public class HomeActivity extends AuthUserAppCompatActivity {
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        continueButton = findViewById(R.id.continueGameButton);
        GetGameFromDB().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(!task.isSuccessful()){
                    continueButton.setEnabled(false);
                }
            }
        });
    }

    public void OnNewGameButtonClick(View v){
        Intent toNew = new Intent(HomeActivity.this, PlayersActivity.class);
        startActivity(toNew);
    }

    public void OnContinueGameButtonClick(View v){
        Intent toGame = new Intent(HomeActivity.this, OverViewActivity.class);
        startActivity(toGame);
    }

    public void OnLogOutButtonClick(View v){

        Intent toLogin = new Intent(HomeActivity.this, LoginActivity.class);
        mFirebaseAuth.signOut();
        startActivity(toLogin);
    }

    public void OnSettingsButtonClick(View v){

        Intent toSettings = new Intent(HomeActivity.this, SettingsActivity.class);
        startActivity(toSettings);
    }

    @Override
    protected Boolean NeedsAuth() {
        return true;
    }
}
