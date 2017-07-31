package com.musicapp.android.allurefrag;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOJISOLA on 26/06/2017.
 */

public class SoundAdapter extends RecyclerView.Adapter<SoundAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView rv;
        public TextView songTitle;
        public TextView songArtist;
        public ImageView musicIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            rv = (RecyclerView) itemView.findViewById(R.id.rvSound);
            songTitle = (TextView) itemView.findViewById(R.id.songtitle_textview);
            songArtist = (TextView) itemView.findViewById(R.id.songartist_textview);
            musicIcon = (ImageView) itemView.findViewById(R.id.music_imageview);
        }
    }
    private List<Sound> mSound;
    private Context mContext;

    public SoundAdapter(Context context, List<Sound> sounds){
        mSound = sounds;
        mContext = context;
    }
    private Context getContext(){
        return mContext;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        // Inflate the custom layout
        View soundView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lists_item, viewGroup, false);
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(soundView);
        return viewHolder;
    }
    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        // Get the data model based on position
        Sound sound = mSound.get(i);

        // Set item views based on your views and data model
        TextView textView = viewHolder.songArtist;
        textView.setText(sound.getSongArtist());
        TextView textView1 = viewHolder.songTitle;
        textView1.setText(sound.getSongTitle());
        ImageView imageView = viewHolder.musicIcon;
        imageView.setImageResource(sound.getImageResourceId());
    }
    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mSound.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    }
