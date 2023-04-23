package com.krzywda;

import java.io.*;

public class FileManager {
    private String filePath;

    public FileManager(String filePath){
        this.filePath = filePath;
    }

    public File createPathFile(){
        File file = new File(this.filePath);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            return file;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public void addPathToFile(File file, String path){
        File mp3File = new File(path);
        if(mp3File.exists() && mp3File.getPath().endsWith(".mp3")) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.newLine();
                writer.write(path);
            } catch (Exception e) {
                System.out.println(e);
            }
        }else {
            System.out.println(mp3File.getPath());
        }
    }
}
