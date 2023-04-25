package com.krzywda;


import javazoom.jl.decoder.JavaLayerException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException, JavaLayerException {
        Player player = new Player();
        boolean flag = true;
        Scanner sc = new Scanner(System.in);

        while(flag){
            System.out.println("Podaj numer 1 - graj; 2 - pauza; 3 - nastepna piosenka; 4 - poprzednia piosenka; 5 - pauza; 6 - wylacz");
            int number = sc.nextInt();
            switch (number) {
                case 1 -> {
                    player.play();
                    System.out.println("dziaala");
                }
                case 2 -> player.stop();
                case 3 -> player.skip();
                case 4 -> player.previous();
                case 5 -> player.pause();
                case 6 -> flag = false;
                default -> {
                }
            }
        }
    }
}
