package com.krzywda;

import java.util.List;

public interface MusicPlayer {
    void play(); //play actual song
    void stop(); //stop play actual sonng
    void skip(); //skip actual song and go to next
    void pause(); //stop play actual song and remember postion
    void previous(); //return to previous song
    void setVolume(int volume); //set volume
    void setPlaylist(Playlist playlist); //set playlist which operation will be performed
    void addToPlayList(Song song); //add song to setted playlist
    void removeFromPlaylist(Song song); //remove song from setted playlist
}
