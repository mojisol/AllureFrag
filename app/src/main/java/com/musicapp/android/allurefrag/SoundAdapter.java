package com.musicapp.android.allurefrag;

import android.app.Activity;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.musicapp.android.allurefrag.R;
import com.musicapp.android.allurefrag.Sound;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOJISOLA on 26/06/2017.
 */

public class SoundAdapter extends RecyclerView.Adapter<SoundAdapter.PersonViewHolder> {

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rv;
        TextView songTitle;
        TextView songArtist;
        ImageView musicIcon;

        PersonViewHolder(View itemView) {
            super(itemView);
            rv = (RecyclerView) itemView.findViewById(R.id.listofsong_recycler_view);
            songTitle = (TextView)itemView.findViewById(R.id.songtitle_textview);
            songArtist = (TextView)itemView.findViewById(R.id.songartist_textview);
            musicIcon = (ImageView)itemView.findViewById(R.id.music_imageview);

        ArrayList<Sound> sounds;
        SoundAdapter(ArrayList<Sound> sounds);
            {sounds = sounds;
            }

        @Override
            public int getItemCount() {
                return sounds.size();
            }
        }
    }


    }
