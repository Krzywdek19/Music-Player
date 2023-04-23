package com.krzywda;


import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;

import java.io.File;
import java.io.InputStream;
import java.util.*;


public class Database {
    ArrayList<Song> songs = new ArrayList<>(100);

    public Database(){
        searchDriveForMp3();
    }


    private void getDataFromFile(File file) {
        try{
            Mp3File mp3File = new Mp3File(file);
            if (mp3File.hasId3v2Tag()){
                ID3v2 id3v2 = mp3File.getId3v2Tag();
                String title = id3v2.getTitle();
                String artist = id3v2.getAlbumArtist();
                String album = id3v2.getAlbum();
                int length = (int)mp3File.getLengthInSeconds();
                String path = file.getPath();
                songs.add(new Song(title,artist,album,length,path));
            }
        }catch (Exception e){
            System.out.println("Something went wrong");
        }
    }

    private void searchDriveForMp3(){
        File file = new File("C:/");
        List<File> files = Arrays.stream(Objects.requireNonNull(file.listFiles())).toList();
        iterateFileList(files);
    }

    private void searchDriveForMp3(File file){
        List<File> files = Arrays.stream(Objects.requireNonNull(file.listFiles())).toList();
        iterateFileList(files);
    }

    private void iterateFileList(List<File> files){
        Iterator<File> iterator = files.listIterator();
        while (iterator.hasNext()){
            File iteratorFile = iterator.next();
            if(iteratorFile.getPath().endsWith(".mp3")){
                this.getDataFromFile(iteratorFile);
            }
            if(iteratorFile.listFiles() != null) {
                searchDriveForMp3(iteratorFile);
            }
        }
    }
}
