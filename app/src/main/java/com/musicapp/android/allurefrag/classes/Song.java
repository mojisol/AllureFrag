package com.musicapp.android.allurefrag.classes;


import android.widget.ImageView;

public class Song {

    private String songTitle, songArtist;
    private int imageResourceId;

    public Song(String songTitle, String songArtist, int imageResourceId) {
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.imageResourceId = imageResourceId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
