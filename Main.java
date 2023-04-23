package com.krzywda;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();
        for(Song song : db.songs){
            song.getInfo();
        }
    }
}
