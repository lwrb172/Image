package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ImageProcessing img = new ImageProcessing();
        long start, end;
        try {
            img.read("thailand1.jpg");
            start = System.currentTimeMillis();
            img.increaseBrightness(50);
            end = System.currentTimeMillis();
            System.out.println("Time: " + (end - start));

            img.read("thailand2.jpg");

            start = System.currentTimeMillis();
            img.increaseBrightness(50);
            end = System.currentTimeMillis();
            System.out.println("Time: " + (end - start));
            img.write("thailand2.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}