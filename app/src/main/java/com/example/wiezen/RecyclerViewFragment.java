package com.example.wiezen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.logic.wiezen.Game;
import com.logic.wiezen.Round;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewFragment extends Fragment implements OnRoundClickListener {
    private static final String TAG = "RecyclerViewFragment";

    private ArrayList<String> mImages = new ArrayList<>();
    private Game game;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.recyclerview_fragment_layout, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((OverViewActivity)getActivity())
                .gameReference
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    game = task.getResult().toObject(Game.class);
                    final TextView p1 = getView().findViewById(R.id.Player1Nameview);
                    final TextView p2 = getView().findViewById(R.id.Player2Nameview);
                    final TextView p3 = getView().findViewById(R.id.Player3Nameview);
                    final TextView p4 = getView().findViewById(R.id.Player4Nameview);

                    p1.setText(game.players.get(0).name);
                    p2.setText(game.players.get(1).name);
                    p3.setText(game.players.get(2).name);
                    p4.setText(game.players.get(3).name);

                    initRecyclerView(getView());
                }
            }
        });
    }

    private void initRecyclerView(View view) {
        Log.d(TAG, "initRecyclerView: init recyclerview");
        initImages();
        RecyclerView recyclerView = view.findViewById(R.id.mainRecycler);
        MainRecyclerViewAdaptor adapter = new MainRecyclerViewAdaptor(getActivity(), game.getRounds(), mImages, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initImages() {
        Log.d(TAG, "initImages: preparing bitmaps");

        mImages.add("https://comps.canstockphoto.com/loser-stamp-drawing_csp15595090.jpg");
        mImages.add("https://www.munters.com/contentassets/070ec072e2ac418cb48a897a1fafc9b1/win.jpg");
    }

    @Override
    public void onRoundClick(int position) {
        Round item = game.rounds.get(position);

        DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.detail);
        if (detailFragment != null && detailFragment.isVisible()) {
            // Visible: send bundle
            DetailFragment newFragment = new DetailFragment();
            Bundle bundle=new Bundle();
            if (item.IsWon){
                bundle.putString("isWon", "Won");
            }
            else{
                bundle.putString("isWon", "Lost");
            }

            bundle.putInt("Round", item.id);
            newFragment.setArguments(bundle);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(detailFragment.getId(), newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
        else {
            // Not visible: start as intent
            Intent intent = new Intent(getActivity().getBaseContext(), DetailActivity.class);
            if (item.IsWon){
                intent.putExtra("isWon", "Won");
            }
            else{
                intent.putExtra("isWon", "Lost");
            }
            intent.putExtra("Round", item.id);
            getActivity().startActivity(intent);
        }
    }
}
