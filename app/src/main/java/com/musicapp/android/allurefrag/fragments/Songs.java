package com.musicapp.android.allurefrag.fragments;


import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.musicapp.android.allurefrag.R;
import com.musicapp.android.allurefrag.adapters.SongsRecyclerAdapter;
import com.musicapp.android.allurefrag.classes.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Songs extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<Song>> {

    private ArrayList<Song> songs;
    private static final int LOADER_ID = 1000;

    private RecyclerView recyclerView;

    private RelativeLayout relativeLayout;

//    private ProgressBar progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.songs, container, false);

        relativeLayout = (RelativeLayout) view.findViewById(R.id.relative_layout);

        recyclerView = (RecyclerView) view.findViewById(R.id.list_of_song_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        LoaderManager loaderManager = getLoaderManager();
        Loader<ArrayList<Song>> loader = loaderManager.getLoader(LOADER_ID);

        if (loader == null){
            loaderManager.initLoader(LOADER_ID, null, this).forceLoad();;
        } else {
            loaderManager.restartLoader(LOADER_ID, null, this).forceLoad();
        }

        return view;
    }

    @Override
    public Loader<ArrayList<Song>> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<ArrayList<Song>>(this.getContext()) {

            @Override
            protected void onStartLoading() {
                super.onStartLoading();
            }

            @Override
            public ArrayList<Song> loadInBackground() {
//                Toast.makeText(getContext(), "loadInBackground", Toast.LENGTH_SHORT).show();
                ArrayList<Song> songs = new ArrayList<>();
                Cursor cursor = getActivity().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        null, MediaStore.Audio.Media.IS_MUSIC + "!=0", null, null);
//                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);

                if (cursor != null){
                    if (cursor.moveToFirst()){
                        do {
//                        MediaStore.Audio.Media.IS_MUSIC
                            Song newSong = new Song(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)),
                                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)),
                                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));
                            songs.add(newSong);
                        }while (cursor.moveToNext());
                        cursor.close();
                    }
                }
                return songs;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Song>> loader, ArrayList<Song> data) {

        relativeLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

        Collections.sort(data, new Comparator<Song>() {
            @Override
            public int compare(Song first, Song second) {
                return first.getSongTitle().compareToIgnoreCase(second.getSongTitle());
            }
        });

        SongsRecyclerAdapter adapter = new SongsRecyclerAdapter(getContext(), data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Song>> loader) {

    }

//    @Override
//    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//        CursorLoader cursorLoader = new CursorLoader(getContext(),
//                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
////        progressDialog = ProgressDialog.show(getContext(), "Getting your music files", "Loading...", false);
////        progressDialog.dismiss();
//        return cursorLoader;
//    }

//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//        relativeLayout.setVisibility(View.GONE);
//        recyclerView.setVisibility(View.VISIBLE);
//        if (data != null && data.getCount() > 0){
//            if (data.moveToFirst()){
////                Toast.makeText(getContext(), data.getCount(), Toast.LENGTH_SHORT).show();
//                do {
//                    Song newSong = new Song(
//                            data.getString(data.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)),
//                            data.getString(data.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)),
//                            data.getString(data.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
//                    songs.add(newSong);
//                    adapter.notifyDataSetChanged();
//                } while (data.moveToNext());
//            }
//        }
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//
//    }
}