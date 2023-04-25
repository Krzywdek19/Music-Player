package com.krzywda;


import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.sound.sampled.*;
import java.io.*;
import java.util.ArrayList;


public class Player implements MusicPlayer {
    private final Database database = new Database();
    private final ArrayList<Playlist> playlists = new ArrayList<>(10);


    private int actualSongIndex = 0;

    private javazoom.jl.player.advanced.AdvancedPlayer playingSong;

    private Playlist actualPlaylist;

    private boolean isPaused = true;

    private int pausedTime = 0;

    @Override
    public void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException, JavaLayerException {
        ArrayList<Song> songs = database.getSongs();
        if (songs.size() > 0) {
            File file = new File(songs.get(this.actualSongIndex).getPath());

            if (file.exists()) {
                InputStream inputStream = new FileInputStream(file);
                Runnable playerRunnable = () -> {
                    try {
                        this.playingSong = new AdvancedPlayer(inputStream);
                        if (isPaused) {
                            System.out.println("po 30 sekundzie");
                            this.playingSong.play(this.pausedTime, Integer.MAX_VALUE); //tu zaimplementuj start piosenki od momentu zatrzymania
                        }else {
                            System.out.println("od razu");
                            this.playingSong.play();
                        }
                    } catch (JavaLayerException e) {
                        throw new RuntimeException(e);
                    }
                };
                new Thread(playerRunnable).start();
            }
        }
    }

    @Override
    public void stop() throws JavaLayerException, FileNotFoundException {
        if (playingSong != null) {
            playingSong.close();
        }
    }

    @Override
    public void skip() throws IOException, JavaLayerException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        System.out.println(this.isPaused);
        if (actualSongIndex + 1 < database.getSongs().size()) {
            actualSongIndex++;
        } else {
            actualSongIndex = 0;
        }
        this.stop();
        this.play();
    }

    @Override
    public void pause() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException, JavaLayerException {
    }

    @Override
    public void previous() throws IOException, JavaLayerException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        if (actualSongIndex - 1 >= 0) {
            actualSongIndex--;
        } else {
            actualSongIndex = database.getSongs().size() - 1;
        }
        this.stop();
        this.play();
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
        if (actualPlaylist != null) {
            this.actualPlaylist.addSongToPlaylist(song);
        }
    }

    @Override
    public void removeFromPlaylist(Song song) {
        if (actualPlaylist != null) {
            this.actualPlaylist.removeSongFromPlaylist(song);
        }
    }
}
