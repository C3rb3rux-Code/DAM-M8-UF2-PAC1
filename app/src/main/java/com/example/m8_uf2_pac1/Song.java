package com.example.m8_uf2_pac1;

import android.graphics.Bitmap;

public class Song {
    public String pathPCP;
    public String namePCP;
    public String timePCP;

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

    public String getNamePCP() {
        return namePCP;
    }

    public void setNamePCP(String namePCP) {
        this.namePCP = namePCP;
    }

    public String getTimePCP() {
        return timePCP;
    }

    public void setTimePCP(String timePCP) {
        this.timePCP = timePCP;
    }
}
