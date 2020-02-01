package com.example.wiezen;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.logic.wiezen.Game;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OverViewActivity extends AuthUserAppCompatActivity {
    private static final String TAG = "OverViewActivity";
    private ArrayList<String> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_view);
        Log.d(TAG, "onCreate: started overview.");

        final TextView p1 = findViewById(R.id.Player1Nameview);
        final TextView p2 = findViewById(R.id.Player2Nameview);
        final TextView p3 = findViewById(R.id.Player3Nameview);
        final TextView p4 = findViewById(R.id.Player4Nameview);

        if(game == null){
             GetGameFromDB().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        game = task.getResult().toObject(Game.class);
                        p1.setText(game.players.get(0).name);
                        p2.setText(game.players.get(1).name);
                        p3.setText(game.players.get(2).name);
                        p4.setText(game.players.get(3).name);
                        initRecyclerView();
                    }
                    else{
                        Toast.makeText(OverViewActivity.this, "Load unsuccesfull", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    protected Boolean NeedsAuth() {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");
        initImages();
        RecyclerView recyclerView = findViewById(R.id.mainRecycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        MainRecyclerViewAdaptor adapter = new MainRecyclerViewAdaptor(game.getRounds(), images, this );
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private void initImages(){
        Log.d(TAG, "initImages: preparing bitmaps");

        images.add("https://upload.wikimedia.org/wikipedia/commons/c/c9/True.svg");
        images.add("https://upload.wikimedia.org/wikipedia/commons/c/c6/False.svg");
    }
}
