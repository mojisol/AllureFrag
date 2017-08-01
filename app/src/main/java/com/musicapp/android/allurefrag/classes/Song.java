package com.musicapp.android.allurefrag.classes;


public class Song {

    private String songTitle, songArtist, songName;

    public Song(String songTitle, String songArtist, String songName) {
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.songName = songName;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public String getSongName() {
        return songName;
    }
}
