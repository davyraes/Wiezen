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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.logic.wiezen.Game;
import com.logic.wiezen.PlayAbleEnum;
import com.logic.wiezen.Player;
import com.logic.wiezen.Round;

import java.util.ArrayList;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class OverViewActivity extends AppCompatActivity {
    private static final String TAG = "OverViewActivity";
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser user;
    private FirebaseFirestore db;
    public DocumentReference gameReference;

    private ArrayList<String> listItems = new ArrayList<>();
    boolean[] checkedItems;
    private ArrayList<Integer> mUserItems = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Player> contestors = new ArrayList<>();
    int hands;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_view);
        Log.d(TAG, "onCreate: started overview.");

        db = FirebaseFirestore.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        user = mFirebaseAuth.getCurrentUser();
        gameReference = db.collection("Games").document(user.getUid());

        if(game == null){
             gameReference
                     .get()
                     .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        Log.d(TAG, "onComplete: game loaded");
                        game = task.getResult().toObject(Game.class);
                    }
                    else{
                        Toast.makeText(OverViewActivity.this, "Load unsuccesfull", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onComplete: cant load ", task.getException());
                    }
                }
            });
        }
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

    @Override
    protected void onPause() {
        super.onPause();
        gameReference.set(game);
    }

    private void setPlayers(final int amountOfPlayers, final PlayAbleEnum play){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle(getString(R.string.AlertTitle));

        listItems.clear();
        players.clear();
        contestors.clear();

        for (Player player :
                game.getPlayers()) {
            listItems.add(player.name);
        }

        checkedItems = new boolean[listItems.size()];
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
        final View view = LayoutInflater.from(this).inflate(R.layout.user_input_layout, null);

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setView(view);

        alertBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText userInput = view.findViewById(R.id.userinput);
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
                            game.rounds.size() + 1,
                            game.rounds.get(game.rounds.size() - 1).playerScores,
                            players,
                            contestors,
                            players.get(0),
                            play,
                            hands,
                            game.configuration);
            game.AddRound(newRound);
        } catch (Exception e) {
            Log.e(TAG, "newRound:" + play.toString(), e);
        }

        gameReference.set(game);
        finish();
        startActivity(getIntent());
    }
}
