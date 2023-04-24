package com.krzywda;

import java.util.ArrayList;
import java.util.Iterator;

public class Playlist {
    private ArrayList<Song> songs = new ArrayList<Song>(10);

    public void addSongToPlaylist(Song song){
        this.songs.add(song);
    }

    public boolean removeSongFromPlaylist(Song song){
        Iterator<Song> iterator = this.songs.iterator();
        while (iterator.hasNext()){
            if(iterator.next().equals(song)){
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
