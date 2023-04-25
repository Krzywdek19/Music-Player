package com.krzywda;




import javazoom.jl.decoder.JavaLayerException;

import javax.sound.sampled.*;
import java.io.*;
import java.util.ArrayList;


public class Player implements MusicPlayer{
    private final Database database = new Database();
    private final ArrayList<Playlist> playlists = new ArrayList<>(10);


    private int actualSongIndex = 0;

    private javazoom.jl.player.Player playingSong;

    private Playlist actualPlaylist;

    @Override
    public void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException, JavaLayerException {
        ArrayList<Song> songs = database.getSongs();
        if (songs.size() > 0) {
            File playingSong = new File(songs.get(this.actualSongIndex).getPath());

            if (playingSong.exists()) {
                InputStream inputStream = new FileInputStream(playingSong);
                Runnable playerRunnable = () -> {
                    try {
                        this.playingSong = new javazoom.jl.player.Player(inputStream);
                        this.playingSong.play();
                    } catch (JavaLayerException e) {
                        throw new RuntimeException(e);
                    }
                };
                new Thread(playerRunnable).start();

            }
        }
        System.out.println("asdasdas");
    }

    @Override
    public void stop() throws JavaLayerException, FileNotFoundException {
        if(playingSong != null){
            playingSong.close();
        }
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
