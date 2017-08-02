package com.musicapp.android.allurefrag.classes;


import android.widget.ImageView;

public class Song {

    private String songTitle, songArtist;
    private int imageResourceId;
    private int audioResourceId;

    public Song(String songTitle, String songArtist, int imageResourceId, int audioResourceId) {
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.imageResourceId = imageResourceId;
        this.audioResourceId = audioResourceId;
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

    public int getAudioResourceId() {
        return audioResourceId;
    }
}
