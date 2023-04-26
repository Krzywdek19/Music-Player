package com.krzywda;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.util.List;

public class SoundPlayer implements MusicPlayer {
    private Player player;
    private Database database = new Database();
    private int playingSongIndex = 0;
    private boolean isPlaying = false;
    private boolean isPaused = false;
    private int pausedTime = 0;

    @Override
    public boolean play() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException, JavaLayerException {
        if(!isPlaying){
            File playingSong = new File(database.getSongs().get(playingSongIndex).getPath());
            if (playingSong.exists()) {
                InputStream inputStream = new FileInputStream(playingSong);
                boolean isPausedForThread = this.isPaused;
                Runnable runnable = () -> {
                    try {
                        if(isPausedForThread){
                            long bytesToSkip = ((long) (this.pausedTime / 1000) *44100*2);
                            inputStream.skip(bytesToSkip);
                            this.player = new Player(inputStream);
                            this.player.play(this.pausedTime);
                        }else {
                            this.player = new Player(inputStream);
                            this.player.play();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                };
                new Thread(runnable).start();
                this.isPaused = false;
                this.isPlaying = true;
            }
        }
        return this.isPlaying;
    }

    @Override

    public void stop() throws JavaLayerException, FileNotFoundException {
        if (isPlaying) {
            this.player.close();
        }
    }

    @Override
    public void skip() throws IOException, JavaLayerException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        this.isPlaying = false;
        this.player.close();
        if(this.playingSongIndex  + 1 < this.database.getSongs().size()){
            this.playingSongIndex++;
        }else {
            this.playingSongIndex = 0;
        }
        this.pausedTime = 0;
        this.play();
    }

    @Override
    public void pause() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException, JavaLayerException {
        if(isPlaying){
            this.pausedTime = this.player.getPosition() + this.pausedTime;
            this.player.close();
            System.out.println(this.pausedTime);
            this.isPlaying = false;
            this.isPaused = true;
        }
    }

    @Override
    public void previous() throws IOException, JavaLayerException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        this.isPlaying = false;
        this.player.close();
        if(this.playingSongIndex  - 1 >= 0){
            this.playingSongIndex--;
        }else {
            this.playingSongIndex = this.database.getSongs().size() - 1;
        }
        this.pausedTime = 0;
        this.play();
    }

    @Override
    public void setVolume(int volume) {

    }

    @Override
    public void setPlaylist(Playlist playlist) {

    }

    @Override
    public void addToPlayList(Song song) {

    }

    @Override
    public void removeFromPlaylist(Song song) {

    }
}
