package com.example.m8_uf2_pac1;

import android.graphics.Bitmap;

public class Song {
    public Bitmap imagePCP;
    public String pathPCP;
    public String namePCP;
    public String timePCP;
    public String artistPCP;
    public String albumPCP;

    public Song() {

    }

    public Song(String songPathPCP, String songNamePCP) {
        this.pathPCP = songPathPCP;
        this.namePCP = songNamePCP;
    }

    public Song(String songPathPCP, String songNamePCP, String songTimePCP) {
        this.pathPCP = songPathPCP;
        this.namePCP = songNamePCP;
        this.timePCP = songTimePCP;
    }

    public Song(Bitmap songImagePCP, String songPathPCP, String songNamePCP, String songTimePCP, String songArtistPCP, String songAlbumPCP) {
        this.imagePCP = songImagePCP;
        this.pathPCP = songPathPCP;
        this.namePCP = songNamePCP;
        this.timePCP = songTimePCP;
        this.artistPCP = songArtistPCP;
        this.albumPCP = songAlbumPCP;
    }
}
