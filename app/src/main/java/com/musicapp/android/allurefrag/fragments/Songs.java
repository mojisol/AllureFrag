package com.musicapp.android.allurefrag.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.musicapp.android.allurefrag.R;
import com.musicapp.android.allurefrag.adapters.SongsRecyclerAdapter;
import com.musicapp.android.allurefrag.classes.Song;

import java.util.ArrayList;


public class Songs extends Fragment {

    public Songs(){
        // Required Empty Public Constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.songs, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_of_song_recycler_view);

        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("Migos","Bad and Boujee",R.raw.migos,R.drawable.music_icon));
        SongsRecyclerAdapter adapter = new SongsRecyclerAdapter(getActivity(), songs);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);

        return view;
    }
}