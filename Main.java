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

        SoundPlayer soundPlayer = new SoundPlayer();
        int index = 0;
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag){
            System.out.println("1 - graj piosenke");
            System.out.println("2 - przestań grać piosenkę");
            System.out.println("3 - zatrzymaj odtwarzanie piosenki");
            System.out.println("4 - nastepna piosenka");
            System.out.println("5 - poprzednia piosenka");
            System.out.println("6 - koniec programu");
            System.out.println("Co chcesz zrobić: ");
            index = sc.nextInt();

            switch (index){
                case 1:
                    soundPlayer.play();
                    break;
                case 2:
                    soundPlayer.stop();
                    break;
                case 3:
                    soundPlayer.pause();
                    break;
                case 4:
                    soundPlayer.skip();
                    break;
                case 5:
                    soundPlayer.previous();
                    break;
                case 6:
                    flag = false;
                    soundPlayer.stop();
                    break;
                default:
                    System.out.println("podales zly numer");
            }
        }
    }
}
