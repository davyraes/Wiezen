package com.example.wiezen;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.logic.wiezen.Player;
import com.logic.wiezen.Round;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainRecyclerViewAdaptor extends RecyclerView.Adapter<MainRecyclerViewAdaptor.ViewHolder>{
    private static final String TAG = "MainRecyclerViewAdaptor";

    private ArrayList<Round> mRounds;
    private ArrayList<String> mImages;
    private Context mContext;

    public MainRecyclerViewAdaptor(ArrayList<Round> mRounds, ArrayList<String> mImages, Context mContext) {
        this.mRounds = mRounds;
        this.mImages = mImages;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recyclerview_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        final Round round = mRounds.get(position);

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(imageNr(mRounds.get(position).IsWon)))
                .into(holder.image);

        ArrayList<Player> players = round.players;
        if (round.contestors != null){
            players.addAll(round.contestors);
        }

        holder.scoreP1.setText(GetPlayerscoreAsString(round, players.get(0)));
        holder.scoreP2.setText(GetPlayerscoreAsString(round, players.get(1)));
        holder.scoreP3.setText(GetPlayerscoreAsString(round, players.get(2)));
        holder.scoreP4.setText(GetPlayerscoreAsString(round, players.get(3)));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Clicked" + mRounds.get(position));
                Toast.makeText(mContext, WonMessage(round.IsWon()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRounds.size();
    }

    private String WonMessage(boolean isWon){
        if (isWon){
            return "Won";
        }else{
            return "Lost";
        }
    }

    private Integer imageNr(boolean isWon){
        if (isWon){
            return 0;
        }else{
            return 1;
        }
    }

    private String GetPlayerscoreAsString(Round round, Player player){
        int score = round.playerScores.get(player.name);
        return Integer.toString(score);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView scoreP1;
        TextView scoreP2;
        TextView scoreP3;
        TextView scoreP4;
        LinearLayout parentLayout;

        public ViewHolder(View itemView){
            super(itemView);
            image = itemView.findViewById(R.id.image);
            scoreP1 = itemView.findViewById(R.id.scoreP1);
            scoreP2 = itemView.findViewById(R.id.scoreP2);
            scoreP3 = itemView.findViewById(R.id.scoreP3);
            scoreP4 = itemView.findViewById(R.id.scoreP4);
            parentLayout = itemView.findViewById(R.id.list_item_layout);
        }

    }
}
