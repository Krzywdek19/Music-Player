package com.krzywda;

import java.util.ArrayList;

public class Player implements MusicPlayer{
    private final Database database = new Database();
    private final ArrayList<Playlist> playlists = new ArrayList<>(10);

    private Playlist actualPlaylist;

    @Override
    public void play() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void skip() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void previous() {

    }

    @Override
    public void setVolume(int volume) {

    }

    @Override
    public void setPlaylist(Playlist playlist) {
        this.actualPlaylist = playlist;
    }

    @Override
    public void addToPlayList(Song song) {
        if (actualPlaylist != null){
            this.actualPlaylist.addSongToPlaylist(song);
        }
    }

    @Override
    public void removeFromPlaylist(Song song) {
        if (actualPlaylist != null){
            this.actualPlaylist.removeSongFromPlaylist(song);
        }
    }
}
