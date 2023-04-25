package com.krzywda;

import javazoom.jl.decoder.JavaLayerException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface MusicPlayer {
    void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException, JavaLayerException; //play actual song
    void stop() throws JavaLayerException, FileNotFoundException; //stop play actual sonng
    void skip(); //skip actual song and go to next
    void pause(); //stop play actual song and remember postion
    void previous(); //return to previous song
    void setVolume(int volume); //set volume
    void setPlaylist(Playlist playlist); //set playlist which operation will be performed
    void addToPlayList(Song song); //add song to setted playlist
    void removeFromPlaylist(Song song); //remove song from setted playlist
}
