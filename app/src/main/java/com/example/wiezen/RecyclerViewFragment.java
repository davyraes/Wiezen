package com.example.wiezen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.logic.wiezen.Game;
import com.logic.wiezen.Round;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewFragment extends Fragment {
    private static final String TAG = "RecyclerViewFragment";

    protected FirebaseAuth mFirebaseAuth;
    protected FirebaseFirestore db;
    protected DocumentReference gameReference;

    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<Round> mRounds = new ArrayList<>();
    private Game game;

    private TextView p1;
    private TextView p2;
    private TextView p3;
    private TextView p4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.recyclerview_fragment_layout, container , false);

        final TextView p1 = view.findViewById(R.id.Player1Nameview);
        final TextView p2 = view.findViewById(R.id.Player2Nameview);
        final TextView p3 = view.findViewById(R.id.Player3Nameview);
        final TextView p4 = view.findViewById(R.id.Player4Nameview);

        db = FirebaseFirestore.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        gameReference = db.collection("Games").document(mFirebaseAuth.getCurrentUser().getUid());

        if(game == null){
            gameReference
                    .get()
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
                                initRecyclerView(view);
                            }
                            else{
                                Toast.makeText(getActivity(), "Load unsuccesfull", Toast.LENGTH_SHORT).show();
                                Log.e(TAG, "onComplete: can load ", task.getException());
                            }
                        }
                    });
        }

        return view;
    }

    private void initRecyclerView(View view){
        Log.d(TAG, "initRecyclerView: init recyclerview");
        initImages();
        RecyclerView recyclerView = view.findViewById(R.id.mainRecycler);
        MainRecyclerViewAdaptor adapter = new MainRecyclerViewAdaptor(getActivity(), game.getRounds(), mImages );
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initImages(){
        Log.d(TAG, "initImages: preparing bitmaps");

        mImages.add("https://comps.canstockphoto.com/loser-stamp-drawing_csp15595090.jpg");
        mImages.add("https://www.munters.com/contentassets/070ec072e2ac418cb48a897a1fafc9b1/win.jpg");
    }
}
