package com.example.wiezen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

public class DetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String isWon = intent.getStringExtra("isWon");
        int round = intent.getIntExtra("Round", 0);

        ((TextView) findViewById(R.id.wonDetail)).setText(isWon);
        if (round != 0){
        ((TextView) findViewById(R.id.RoundDetail)).setText(Integer.toString(round));
        }
    }
}
