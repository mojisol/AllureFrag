package com.musicapp.android.allurefrag.fragments;


import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.musicapp.android.allurefrag.R;
import com.musicapp.android.allurefrag.adapters.SongsRecyclerAdapter;
import com.musicapp.android.allurefrag.classes.Song;

import java.util.ArrayList;


public class Songs extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private ArrayList<Song> songs;
    private static final int LOADER_ID = 1000;

    private RecyclerView recyclerView;

    private SongsRecyclerAdapter adapter;

    private RelativeLayout relativeLayout;

//    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.songs, container, false);

        relativeLayout = (RelativeLayout) view.findViewById(R.id.relative_layout);

        songs = new ArrayList<>();

        getLoaderManager().initLoader(LOADER_ID, null, this);

        adapter = new SongsRecyclerAdapter(getContext(), songs);

        Toast.makeText(getContext(), Integer.toString(songs.size()), Toast.LENGTH_SHORT).show();

        recyclerView = (RecyclerView) view.findViewById(R.id.list_of_song_recycler_view);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader = new CursorLoader(getContext(),
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
//        progressDialog = ProgressDialog.show(getContext(), "Getting your music files", "Loading...", false);
//        progressDialog.dismiss();
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        relativeLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        if (data != null && data.getCount() > 0){
            if (data.moveToFirst()){
                Toast.makeText(getContext(), data.getCount(), Toast.LENGTH_SHORT).show();
                do {
                    Song newSong = new Song(
                            data.getString(data.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)),
                            data.getString(data.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)),
                            data.getString(data.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
                    songs.add(newSong);
                    adapter.notifyDataSetChanged();
                } while (data.moveToNext());
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}