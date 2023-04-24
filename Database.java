package com.krzywda;


import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;

import java.io.*;
import java.util.*;


public class Database {
    ArrayList<Song> songs = new ArrayList<>(100);
    FileManager fileManager = new FileManager("./src/main/java/com/krzywda/paths.txt");

    public Database(){
        getFilesFromMusicFolder();
        transferDataToList();
    }

    private void transferDataToList(){
        try (BufferedReader reader = new BufferedReader(new FileReader(fileManager.createPathFile()))){
            while(reader.ready()){
                String path = reader.readLine();
                getSongInfo(path);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    private void getSongInfo(String path) {
        File file = new File(path);
        if(file.canRead()) {
            try {
                Mp3File mp3File = new Mp3File(file);
                if (mp3File.hasId3v2Tag()) {
                    ID3v2 id3v2 = mp3File.getId3v2Tag();
                    String title = id3v2.getTitle();
                    String artist = id3v2.getAlbumArtist();
                    String album = id3v2.getAlbum();
                    int length = (int) mp3File.getLengthInSeconds();
                    this.songs.add(new Song(title, artist, album, length, path));
                } else {
                    this.songs.add(new Song(file.getPath(), (int) mp3File.getLengthInSeconds()));
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println(file.getPath());
            }
        }
    }

    public void getFilesFromMusicFolder(){
        String homeFolderParh = System.getProperty("user.home");
        File musicFolder = new File(homeFolderParh + File.separator + "music");
        System.out.println(musicFolder.exists());
        iterateFileList(Arrays.stream(Objects.requireNonNull(musicFolder.listFiles())).toList());
    }


    public void searchDriveForMp3(){
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
            System.out.println(iteratorFile.getPath());
            if(iteratorFile.getPath().endsWith(".mp3")){
                try{
                    if(new Mp3File(iteratorFile).getLengthInSeconds() > 30) {
                        fileManager.addPathToFile(fileManager.createPathFile(), iteratorFile.getPath());
                    }
                }catch (Exception e){
                    System.err.println("Błąd podczas zapisywania danych do pliku: " + e.getMessage());
                }
            }
            if(iteratorFile.listFiles() != null) {
                searchDriveForMp3(iteratorFile);
            }
        }
    }
}
