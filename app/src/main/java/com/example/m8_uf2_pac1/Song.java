package com.example.m8_uf2_pac1;

public class Song {
    private String namePCP;
    private String timePCP;

    public Song () {

    }

    public Song (String songNamePCP, String songTimePCP) {
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
