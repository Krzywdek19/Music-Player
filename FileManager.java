package com.krzywda;

import java.io.*;

public class FileManager {
    private final String filePath;


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
        if(!fileContain(file, path)){
            File mp3File = new File(path);
            if(mp3File.exists() && mp3File.getPath().endsWith(".mp3")) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                    if(file.length() > 0){
                        writer.newLine();
                    }
                    writer.write(path);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    static boolean fileContain(File file, String content){
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            while(reader.ready()){
                if(reader.readLine().equals(content)){
                    return true;
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
}
