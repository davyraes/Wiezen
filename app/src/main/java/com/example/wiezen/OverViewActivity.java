package com.example.wiezen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.logic.wiezen.Game;
import com.logic.wiezen.PlayAbleEnum;
import com.logic.wiezen.Player;
import com.logic.wiezen.Round;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OverViewActivity extends AuthUserAppCompatActivity {
    private static final String TAG = "OverViewActivity";
    private ArrayList<String> images = new ArrayList<>();
    ArrayList<String> listItems = new ArrayList<>();
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Player> contestors = new ArrayList<>();
    int hands;

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
             GetGameFromDB()
                     .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        Log.d(TAG, "onComplete: game loaded");
                        game = task.getResult().toObject(Game.class);
                        p1.setText(game.players.get(0).name);
                        p2.setText(game.players.get(1).name);
                        p3.setText(game.players.get(2).name);
                        p4.setText(game.players.get(3).name);
                        initRecyclerView();

                        for (Player player :
                                game.getPlayers()) {
                            listItems.add(player.name);
                        }

                        checkedItems = new boolean[listItems.size()];
                    }
                    else{
                        Toast.makeText(OverViewActivity.this, "Load unsuccesfull", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onComplete: can load ", task.getException());
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.MenuNewGame:
                Intent toNewGame = new Intent(OverViewActivity.this, PlayersActivity.class);
                startActivity(toNewGame);
                break;
            case R.id.Regular1P:
                setPlayers(1, PlayAbleEnum.REGULAR);
                break;
            case R.id.Regular2P:
                setPlayers(2, PlayAbleEnum.REGULAR);
                break;
            case R.id.dans9:
                setPlayers(1, PlayAbleEnum.DANS_9);
                break;
            case R.id.dans10:
                setPlayers(1, PlayAbleEnum.DANS_10);
                break;
            case R.id.dans11:
                setPlayers(1, PlayAbleEnum.DANS_11);
                break;
            case R.id.dans12:
                setPlayers(1, PlayAbleEnum.DANS_12);
                break;
            case R.id.RegularMisery:
                setPlayers(1, PlayAbleEnum.MISERY);
                break;
            case R.id.OpenMisery:
                setPlayers(1, PlayAbleEnum.MISERY_OPEN);
                break;
            case R.id.Troul:
                setPlayers(2, PlayAbleEnum.TROUL);
                break;
            case R.id.RegularSolo:
                setPlayers(1, PlayAbleEnum.SOLO);
                break;
            case R.id.SoloSlim:
                setPlayers(1, PlayAbleEnum.SOLO_SLIM);
                break;
            case R.id.MenuLogOut:
                Intent toLogin = new Intent(OverViewActivity.this, LoginActivity.class);
                mFirebaseAuth.signOut();
                startActivity(toLogin);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");
        initImages();
        RecyclerView recyclerView = findViewById(R.id.mainRecycler);
        MainRecyclerViewAdaptor adapter = new MainRecyclerViewAdaptor(this, game.getRounds(), images );
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initImages(){
        Log.d(TAG, "initImages: preparing bitmaps");

        images.add("https://www.munters.com/contentassets/070ec072e2ac418cb48a897a1fafc9b1/win.jpg");
        images.add("https://comps.canstockphoto.com/loser-stamp-drawing_csp15595090.jpg");
    }

    private void setPlayers(final int amountOfPlayers, final PlayAbleEnum play){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle(getString(R.string.AlertTitle));
        mBuilder.setMultiChoiceItems(listItems.toArray(new String[listItems.size()]), checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked){
                    if (!mUserItems.contains(which)){
                        mUserItems.add(which);
                    }
                    else{
                        mUserItems.remove(which);
                    }
                }
            }
        });

        mBuilder.setCancelable(false);
        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = 0; i < mUserItems.size(); i++){
                    players.add(game.players.get(i));
                }
                checkAmountPlayers(amountOfPlayers);
                setContestors();
                setHands(play);
            }
        });

        mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        mBuilder.setNeutralButton("clear all", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = 0; i < checkedItems.length; i++){
                    checkedItems[i] = false;
                    mUserItems.clear();
                    players.clear();
                    contestors.clear();
                }
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void setContestors(){
        for (Player player : game.getPlayers()) {
            if (!players.contains(player)){
                contestors.add(player);
            }
        }
    }

    private void checkAmountPlayers(int amountOfPlayers){
        if (players.size() > amountOfPlayers){
            Log.e(TAG, "checkAmountPlayers: To much players", new Exception());
        }else if (players.size() == 0){
            Log.e(TAG, "checkAmountPlayers: no players", new Exception());
        }
    }

    private void setHands(final PlayAbleEnum play){
        View view = LayoutInflater.from(this).inflate(R.layout.user_input_layout, null);

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setView(view);

        final EditText userInput = findViewById(R.id.userinput);
        alertBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String temp= userInput.getText().toString();
                if (!temp.isEmpty()){
                    hands = Integer.parseInt(temp);
                    if (hands > 13 || hands < 0){
                        Log.e(TAG, "onClick: invalid hands", new Exception());
                        setHands(play);
                    }

                    newRound(play);
                }
            }
        });

        Dialog dialog = alertBuilder.create();
        dialog.show();
    }

    private void newRound(PlayAbleEnum play){
        try {
            Round newRound =
                    new Round(
                            game.rounds.size()+1,
                            game.rounds.get(game.rounds.size()-1).playerScores,
                            players,
                            contestors,
                            null,
                            play,
                            hands,
                            game.configuration);
            game.rounds.add(newRound);
        } catch (Exception e) {
            Log.e(TAG, "newRound:" + play.toString(), e);
        }

        SaveGameToDb(game);
        Intent intent = new Intent(OverViewActivity.this, OverViewActivity.class);
        startActivity(intent);
    }
}
