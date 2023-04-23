package com.krzywda;

public class Song {
    private String title;
    private String artist;
    private String album;
    private int lengthInSec;
    private String path;

    public Song(String title, String artist, String album, int lengthInSec, String path){
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.lengthInSec = lengthInSec;
        this.path = path;
    }

    public Song(String path, int lengthInSec){
        this.path = path;
        this.lengthInSec = lengthInSec;
        this.title = null;
        this.artist = null;
        this.album = null;
    }
    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public int getLengthInSec() {
        return lengthInSec;
    }

    public String getPath() {
        return path;
    }

    public void getInfo(){
        System.out.println("Tytuł: " + this.title);
        System.out.println("Wykonawca: " + this.artist);
        System.out.println("Album: " + this.album);
        System.out.println("Dlugosc: " + this.lengthInSec/60 + ":" + lengthInSec%60);
        System.out.println("Sciezka: " + this.path);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        Song otherSong = (Song)obj;
        return otherSong.getPath().equals(this.getPath());
    }
}
