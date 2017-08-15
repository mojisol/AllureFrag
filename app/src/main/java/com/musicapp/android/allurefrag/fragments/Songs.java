package com.musicapp.android.allurefrag.fragments;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.PermissionChecker;
import android.support.v7.widget.LinearLayoutManager;
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

    public Songs(){
        // Required Empty Public Constructor
    }

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

        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("Migos","Bad and Boujee","Rap"));
        SongsRecyclerAdapter adapter = new SongsRecyclerAdapter(getActivity(), songs);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (PermissionChecker.checkSelfPermission(getActivity(),Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Explain to the user why we need to read the contacts
            }

            requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

            // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
            // app-defined int constant that should be quite unique

            return;
        }

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