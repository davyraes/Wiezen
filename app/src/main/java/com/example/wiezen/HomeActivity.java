package com.example.wiezen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    @Override
    protected Boolean NeedsAuth() {
        return true;
    }
}
