package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ImageProcessing img = new ImageProcessing();
        try {
            img.read("thailand1.jpg");
            img.increaseBrightness(50);
            img.write("thailand2.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}