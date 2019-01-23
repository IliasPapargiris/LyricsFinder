/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Blob;

/**
 *
 * @author User
 */
public class Song {
    String title;
    String artist;
    String lyrics;
    Blob mp3;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public void setMp3(Blob mp3) {
        this.mp3 = mp3;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getLyrics() {
        return lyrics;
    }

    public Blob getMp3() {
        return mp3;
    }
    
    
}
