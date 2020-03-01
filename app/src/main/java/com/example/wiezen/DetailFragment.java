package com.example.wiezen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {
    private static final String TAG = "DetailFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment_layout, container, false);

        Bundle bundle = getArguments();
        TextView wonText = view.findViewById(R.id.wonDetail);
        TextView roundText = view.findViewById(R.id.RoundDetail);
        int round;
        String won;

        if(bundle != null){
            won = getArguments().getString("isWon");
            round = getArguments().getInt("Round");

            wonText.setText(won);
            roundText.setText(Integer.toString(round));
        }

        return view;
    }
}
