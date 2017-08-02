package com.musicapp.android.allurefrag.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.musicapp.android.allurefrag.R;


public class Songs extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.songs, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_of_song_recycler_view);

        return view;
    }
}