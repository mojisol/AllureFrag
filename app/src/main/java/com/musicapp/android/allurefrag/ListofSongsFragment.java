package com.musicapp.android.allurefrag;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Scene;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListofSongsFragment extends Fragment {
    private ArrayList<Sound> sounds;
    private RecyclerView rv;
    private SoundAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        rv= (RecyclerView) view.findViewById(R.id.rvSound);
        // ...
        // Lookup the recyclerview in activity layout
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        // Initialize contacts
        sounds = Sound.createSoundList(20);
        // Create adapter passing in the sample user data
        SoundAdapter adapter = new SoundAdapter(getActivity(),sounds);
        // Attach the adapter to the recyclerview to populate items
        rv.setAdapter(adapter);
        // That's all!
        return view;
    }
    
}


