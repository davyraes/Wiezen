package com.example.wiezen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.logic.wiezen.Round;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class OverViewActivity extends AuthUserAppCompatActivity {
    private static final String TAG = "OverViewActivity";
    private ArrayList<String> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_view);
        Log.d(TAG, "onCreate: started overview.");
        GetGameFromDB();
        initRecyclerView();
    }

    @Override
    protected Boolean NeedsAuth() {
        return true;
    }

    public void OnReturnButtonClick(View v){
        Intent toHome = new Intent(OverViewActivity.this, HomeActivity.class);
        startActivity(toHome);
    }

    public void OnNewRoundButtonClick(View v){

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");
        initImages();
        RecyclerView recyclerView = findViewById(R.id.mainRecycler);
        MainRecyclerViewAdaptor adapter = new MainRecyclerViewAdaptor(game.getRounds(), images, this );
        recyclerView.setAdapter(adapter);
    }

    private void initImages(){
        Log.d(TAG, "initImages: preparing bitmaps");

        images.add("https://upload.wikimedia.org/wikipedia/commons/c/c9/True.svg");
        images.add("https://upload.wikimedia.org/wikipedia/commons/c/c6/False.svg");
    }
}
